# Self-Hosting Eval-It

The backend is fully containerized. Docker Compose is the recommended way to run it in production.

## Prerequisites

- [Docker](https://docs.docker.com/get-docker/) & [Docker Compose](https://docs.docker.com/compose/install/)
- [Google Cloud CLI](https://cloud.google.com/sdk/docs/install)
- Google Cloud account

## 1. Google Cloud Setup

The server integrates with **Google Forms API**, **Google Drive API**, and **Cloud Pub/Sub**. You need a GCP project with all three enabled.

### Enable APIs

In [APIs & Services → Library](https://console.cloud.google.com/apis/library), enable:
- Google Forms API
- Google Drive API
- Cloud Pub/Sub API

### Create OAuth 2.0 Credentials

1. Go to **APIs & Services → Credentials → Create Credentials → OAuth 2.0 Client ID**.
2. Set **Application type** to **Web application**.
3. Add your server's callback URL as an **Authorized redirect URI**.
   - **If testing locally:** Add 
      - `http://localhost:8080/google/oauth/callback`
      - `http://localhost:8080/login/oauth2/code/google`
      - `http://127.0.0.1.nip.io:8080/google/oauth/callback`
      - `http://127.0.0.1.nip.io:8080/login/oauth2/code/google`
   - **If running on a remote server:** Add
      - `http://<YOUR_SERVER_IP>.nip.io:8080/google/oauth/callback` 
      - `http://<YOUR_SERVER_IP>.nip.io:8080/login/oauth2/code/google`

4. Note the **Client ID** and **Client Secret** — you will need them in `.env`.

### Configure OAuth Consent Screen

Go to **APIs & Services → OAuth consent screen** and add these scopes:
- `https://www.googleapis.com/auth/forms.body`
- `https://www.googleapis.com/auth/drive`
- `https://www.googleapis.com/auth/drive.file`
- `https://www.googleapis.com/auth/forms.responses.readonly`

Add the Google account you will use at startup as a **test user** if the app is in testing mode.

### Create Pub/Sub Topic & Subscription

```bash
gcloud config set project YOUR_PROJECT_ID
gcloud pubsub topics create responses
gcloud pubsub subscriptions create responses-sub --topic=responses
```

### Grant Forms Service Account Publish Permission

Google Forms publishes response notifications to your topic. Grant it permission:

```bash
gcloud pubsub topics add-iam-policy-binding responses \
  --member="serviceAccount:forms-notifications@system.gserviceaccount.com" \
  --role="roles/pubsub.publisher"
```

### Authenticate with Application Default Credentials

The server uses ADC to subscribe to Pub/Sub. Run this once on the host machine:

```bash
gcloud auth application-default login
```

## 2. Environment Configuration

Copy `.env.example` to `.env` and fill in all required values:

```bash
cp .env.example .env
```

Every variable is documented with a description inside `.env.example`.

## 3. Start the Server

```bash
docker-compose up -d --build
```

This starts both MySQL and the Spring Boot server. The API will be available on port `8080`.

## 4. Authorize Google APIs (first run only)

After the server starts, open the following URL in a browser and sign in with the Google account that owns the GCP project (make sure to include the port if testing locally):

```
http://<YOUR_SERVER_IP>.nip.io:8080/google/oauth/start
```

A refresh token is saved automatically — this step only needs to be repeated if the token file is deleted.
