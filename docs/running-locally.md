# Running the Client Locally

This guide is for contributors and developers who want to build and run the JavaFX client from source. End users should download a pre-built installer from the [Releases](https://github.com/your-org/eval-it/releases) page instead.

## Prerequisites

- **Java 25** (JDK)
- **Maven** — or use the included `mvnw` / `mvnw.cmd` wrapper (no separate install needed)

## Environment Configuration

Copy `.env.example` to `.env` in the project root and fill in the values:

```bash
cp .env.example .env
```

- `SERVER_IP` — the base URL the client uses to make API calls (e.g. `http://localhost:8080` for local development).

## macOS / Linux

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

## Windows

```bash
# Install the root parent POM (first time only)
.\mvnw -N clean install

# Install the shared module (first time or after shared changes)
.\mvnw -pl shared clean install

# Start the client
.\mvnw -pl client clean javafx:run
```
