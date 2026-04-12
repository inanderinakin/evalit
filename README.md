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

- **Java 25** (JDK)

## Configuration

### Client (`.env` file in project root)

- `SERVER_IP` — the server address (e.g. `http://localhost:8080`)
- `SERVER_IP_WITH_NIP` — alternative address used for OAuth redirect

## Running the Client

### macOS / Linux

```bash
# Install shared module (first time or after shared changes)
./mvnw -pl shared clean install

# Start the client
./mvnw -pl client clean javafx:run
```

### Windows

```bash
# Install shared module
.\mvnw -pl shared clean install

# Start the client
.\mvnw -pl client clean javafx:run
```

## Running the Server

By default, Fullhouse serves the server side on a rented Windows Server whose IP is located in the `.env` file (`.env` is included even though it contains confidential information so that TAs can see the IP). To run the server on another device, a comprehensive setup is needed:

### Server Prerequisites

- **MySQL Server 8.0** installed and running, with a database created for the application
- **Google Cloud CLI** — for Pub/Sub authentication ([install guide](https://cloud.google.com/sdk/docs/install))

### Server Configuration (`server/src/main/resources/application.properties`)

This file is gitignored because it is special for each device. You must create it manually with:

- `spring.datasource.url` — JDBC URL for your MySQL database
- `spring.datasource.username` / `spring.datasource.password` — database credentials
- `spring.jpa.hibernate.ddl-auto` — schema generation strategy (e.g. `update`)
- `google.oauth.client-id` / `google.oauth.client-secret` — Google OAuth credentials
- `google.oauth.redirect-uri` — e.g. `http://localhost:8080/google/oauth/callback`
- Spring Security OAuth2 client registration properties

An example application.properties file is included at `server/src/main/resources/application.properties.example`.

### One-Time Google Cloud Setup

Authenticate for Pub/Sub (only needed once per machine):

```bash
gcloud auth application-default login
```

Sign in with the project's Google account and select the **eval-it** project.
(If the specific Google Account's information is needed, 
please mail to inan.akin@ug.bilkent.edu.tr and we will provide it for you)

### Starting the Server

#### macOS / Linux

```bash
./mvnw -pl shared clean install
./mvnw -pl server clean spring-boot:run
```

#### Windows

```bash
.\mvnw -pl shared clean install
.\mvnw -pl server clean spring-boot:run
```

### Post-Startup OAuth Setup

After the server starts, open `http://localhost:8080/google/oauth/start` in your browser and log in with the project's Google account. This authorizes the server to use Google Forms and Drive APIs. This step is needed each time the server restarts.

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
