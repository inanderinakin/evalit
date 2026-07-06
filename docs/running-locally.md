# Running the Client Locally

This guide is for contributors and developers who want to build and run the JavaFX client from source. End users should download a pre-built installer from the [Releases](https://github.com/your-org/eval-it/releases) page instead.

## Prerequisites

- **Java 25** (JDK)

## Environment Configuration

Copy `.env.example` to `.env` if you haven't already in the project root and fill in the values:

```bash
cp .env.example .env
```


## macOS / Linux

```bash
# Make the Maven wrapper executable (first time only)
chmod +x mvnw

# Install all modules (shared, server, and client)
./mvnw clean install

# Start the client
./mvnw -pl client clean javafx:run
```

> If `./mvnw` gives "operation not permitted" even after `chmod +x`, run using `sh` instead: `sh mvnw -N clean install`, etc.

## Windows

```bash
# Install all modules (shared, server, and client)
.\mvnw clean install

# Start the client
.\mvnw -pl client clean javafx:run
```
