# Eval-It

A survey-based business evaluation platform. Business owners create and apply surveys to their businesses; users browse a survey marketplace, scan QR codes to fill out Google Forms, and scores are aggregated automatically.

Built with a JavaFX desktop client and a Spring Boot REST API backend.

## Features

- **Google OAuth 2.0 login**: login easily with already existing Google accounts.
- **Survey Marketplace**:  browse, search, and filter community-created survey templates by category.
- **Dynamic Survey Creation**: surveys are turned into Google Forms automatically on the server.
- **QR Code Generation**: business owners get a QR code linking to their active survey form.
- **Real-time Score Aggregation**: responses are captured via Google Cloud Pub/Sub and scores are computed per-question and overall.
- **Business Profiles**: logo upload, address, phone, average rating across all applied surveys.
- **Admin Panel**: for admins only; ban users, remove businesses/surveys, review reported surveys.

## Tech Stack

| Layer | Technology |
|-------|------------|
| Client | JavaFX 25, Jackson, ZXing (QR codes), dotenv-java |
| Server | Spring Boot 4.0.3, Spring Security OAuth2, Spring Data JPA |
| Database | MySQL 8.0 |
| External APIs | Google Forms API, Google Drive API, Google Cloud Pub/Sub |
| Build & Deployment| Maven (multi-module), Java 25, Docker, Docker Compose |

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

## Releases

> **Web app coming soon.** A browser-based version of Eval-It is in development and will become the primary way to use the platform — no installation required.

In the meantime, pre-built installers for the desktop client are available on the [GitHub Releases](../../releases) page:

| Platform | Format |
|----------|--------|
| macOS | `.dmg` disk image |
| Windows | `.msi` installer |

## Contributing

Want to build from source or run the server locally? See the docs:

- [Running the Client Locally](docs/running-locally.md)
- [Self-Hosting Guide](docs/self-hosting.md)

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
