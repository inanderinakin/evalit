# Eval-It

A survey-based business evaluation platform. Business owners create and apply surveys to their businesses; users browse a survey marketplace, scan QR codes to fill out Google Forms, and scores are aggregated automatically.

Built with a JavaFX desktop client and a Spring Boot REST API backend.

## Features

- **Google OAuth 2.0 login** login easily with already existing Google accounts.
- **Survey Marketplace** — browse, search, and filter community-created survey templates by category.
- **Dynamic Survey Creation** — surveys are turned into Google Forms automatically on the server.
- **QR Code Generation** — business owners get a QR code linking to their active survey form.
- **Real-time Score Aggregation** — responses are captured via Google Cloud Pub/Sub and scores are computed per-question and overall.
- **Business Profiles** — logo upload, address, phone, average rating across all applied surveys.
- **Admin Panel** — for admins only; ban users, remove businesses/surveys, review reported surveys.

## Tech Stack

| Layer | Technology |
|-------|------------|
| Client | JavaFX 25, Jackson, ZXing (QR codes), dotenv-java |
| Server | Spring Boot 4.0.3, Spring Security OAuth2, Spring Data JPA |
| Database | MySQL |
| External APIs | Google Forms API, Google Drive API, Google Cloud Pub/Sub |
| Build | Maven (multi-module), Java 25 |

## Project Structure

```
eval-it/
├── shared/     Shared DTOs and enums used by both client and server
├── client/     JavaFX desktop application
├── server/     Spring Boot REST API
├── uploads/    Uploaded business logos
├── mvnw        Maven wrapper (Unix)
└── mvnw.cmd    Maven wrapper (Windows)
```

## Prerequisites

### All platforms
- **Java 25** (JDK)
- **Maven** (or use the included `mvnw` / `mvnw.cmd` wrapper — no separate install needed)

### Server only
- **MySQL Server 8.0+** — installed and running locally
- **Google Cloud CLI** — for Pub/Sub authentication ([install guide](https://cloud.google.com/sdk/docs/install))
- A **Google Cloud project** with the required APIs enabled (see [Google Cloud Setup](#google-cloud-setup) below)

## Running the Client

### Client Configuration (`.env` file in project root)

The `.env` file in the project root is read by the client at startup. By default it points to the project's hosted server. If you want to run the server locally instead, update both values to use `localhost`:

```
SERVER_IP=http://localhost:8080
SERVER_IP_WITH_NIP=http://localhost.nip.io:8080
```

`SERVER_IP_WITH_NIP` must be a hostname (not a bare IP) because it is used as the OAuth redirect base — `nip.io` is a free DNS service that maps `<ip>.nip.io` back to `<ip>`.

### macOS / Linux

```bash
# Make the Maven wrapper executable (first time only)
chmod +x mvnw

# Install the root parent POM (first time only)
./mvnw -N clean install

# Install the shared module (first time or after shared changes)
./mvnw -pl shared clean install

# Start the client
./mvnw -pl client clean javafx:run
```

> If `./mvnw` gives "operation not permitted" even after `chmod +x`, run using `sh` instead: `sh mvnw -N clean install`, etc.

### Windows

```bash
# Install the root parent POM (first time only)
.\mvnw -N clean install

# Install the shared module (first time or after shared changes)
.\mvnw -pl shared clean install

# Start the client
.\mvnw -pl client clean javafx:run
```

## Running the Server

Follow every step below in order.

### 1. MySQL Setup

1. Install and start MySQL Server 8.0+.
2. Note your MySQL username and password. The database (`evalit`) will be created automatically on first startup.

### 2. Google Cloud Setup

The server integrates with three Google Cloud services: **Google Forms API**, **Google Drive API**, and **Cloud Pub/Sub**. All three must be enabled in the same GCP project.

> **Note on hardcoded project ID**: The GCP project ID `eval-it-490310` is currently hardcoded in two source files:
> - `server/src/main/java/com/fullhouse/server/services/SurveyResponseHandlerService.java` (line ~30, field `projectId`)
> - `server/src/main/java/com/fullhouse/server/services/SurveyServiceImpl.java` (inside `createWatch`, the Pub/Sub topic name)
>
> If you use a different GCP project, update both of these values before building.

#### 2a. Create a GCP project

1. Go to [https://console.cloud.google.com](https://console.cloud.google.com) and create a new project (or use an existing one).
2. Note the **Project ID**.

#### 2b. Enable required APIs

In your project, enable the following APIs (search for each in **APIs & Services → Library**):

- Google Forms API
- Google Drive API
- Cloud Pub/Sub API

#### 2c. Create OAuth 2.0 credentials

1. Go to **APIs & Services → Credentials → Create Credentials → OAuth 2.0 Client ID**.
2. Set **Application type** to **Web application**.
3. Under **Authorized redirect URIs**, add:
   ```
   http://localhost:8080/google/oauth/callback
   ```
4. Click **Create**. Download or copy the **Client ID** and **Client Secret** — you will need them in `application.properties`.

#### 2d. Configure the OAuth consent screen

1. Go to **APIs & Services → OAuth consent screen**.
2. Set the scopes to include:
   - `https://www.googleapis.com/auth/forms.body`
   - `https://www.googleapis.com/auth/drive`
   - `https://www.googleapis.com/auth/drive.file`
   - `https://www.googleapis.com/auth/forms.responses.readonly`
3. Add the Google account you will use at startup as a **test user** (if the app is in testing mode).

#### 2e. Create the Pub/Sub topic and subscription

The server expects a topic named `responses` and a pull subscription named `responses-sub`.

```bash
gcloud config set project YOUR_PROJECT_ID

# Create the topic
gcloud pubsub topics create responses

# Create the pull subscription
gcloud pubsub subscriptions create responses-sub --topic=responses
```

#### 2f. Grant the Google Forms service account publish permission

Google Forms sends response notifications by publishing to your Pub/Sub topic. You must grant it permission:

```bash
gcloud pubsub topics add-iam-policy-binding responses \
  --member="serviceAccount:forms-notifications@system.gserviceaccount.com" \
  --role="roles/pubsub.publisher"
```

#### 2g. Authenticate the server with Application Default Credentials

The server uses your local ADC to subscribe to Pub/Sub. Run this once per machine:

```bash
gcloud auth application-default login
```

Sign in with the Google account that owns (or has editor access to) the GCP project, and select the correct project when prompted.

### 3. Server Configuration

Create the file `server/src/main/resources/application.properties` by copying the example:

```bash
cp server/src/main/resources/application.properties.example \
   server/src/main/resources/application.properties
```

Then fill in all placeholder values:

| Property | Description |
|---|---|
| `spring.datasource.url` | JDBC URL, e.g. `jdbc:mysql://localhost:3306/evalit` |
| `spring.datasource.username` | MySQL username |
| `spring.datasource.password` | MySQL password |
| `spring.jpa.hibernate.ddl-auto` | Use `update` to auto-create/update schema |
| `google.oauth.client-id` | OAuth 2.0 Client ID from step 2c |
| `google.oauth.client-secret` | OAuth 2.0 Client Secret from step 2c |
| `google.oauth.redirect-uri` | `http://localhost:8080/google/oauth/callback` |
| `spring.security.oauth2.client.registration.google.client-id` | Same as `google.oauth.client-id` |
| `spring.security.oauth2.client.registration.google.client-secret` | Same as `google.oauth.client-secret` |
| `spring.mail.username` | Gmail address used to send verification emails |
| `spring.mail.password` | Gmail [App Password](https://support.google.com/accounts/answer/185833) (not your regular password) |

This file is gitignored and must never be committed.

### 4. Build and Start the Server

#### macOS / Linux

```bash
chmod +x mvnw
./mvnw -N clean install
./mvnw -pl shared clean install
./mvnw -pl server clean spring-boot:run
```

#### Windows

```bash
.\mvnw -N clean install
.\mvnw -pl shared clean install
.\mvnw -pl server clean spring-boot:run
```

### 5. Post-Startup: Authorize Google Forms & Drive Access

After the server starts, open the following URL in your browser:

```
http://localhost:8080/google/oauth/start
```

Sign in with the Google account from step 2g. This grants the server access to the Google Forms and Drive APIs and saves a refresh token to `server/secrets/google-refresh-token.txt`. **This step must be repeated each time the server restarts** (or until a valid refresh token is already present in that file).

## Dependencies

### Client
- JavaFX 25 (`javafx-controls`, `javafx-fxml`, `javafx-graphics`, `javafx-swing`)
- Jackson Databind 2.17.2
- ZXing 3.5.3 (QR code generation)
- dotenv-java 3.0.0

### Server
- Spring Boot 4.0.3 (Web MVC, Security, Data JPA, Mail, OAuth2 Client)
- MySQL Connector/J
- Google API Client Libraries (Forms v1, Drive v3)
- Google Auth Library 1.43.0
- Google Cloud Pub/Sub 1.149.0
