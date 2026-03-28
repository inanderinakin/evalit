
# Evalit

## Getting Started

### Windows

First-time setup:
```bash
.\mvnw -N install
```

Run the server:
```bash
.\mvnw -pl server clean spring-boot:run
```

Run the client:
```bash
.\mvnw -pl client clean javafx:run
```

### macOS / Linux

First-time setup:
```bash
./mvnw -N install
```

Run the server:
```bash
./mvnw -pl server clean spring-boot:run
```

Run the client:
```bash
./mvnw -pl client clean javafx:run
```

### Troubleshooting

If the server fails to compile, authenticate with Google Cloud:
```bash
gcloud auth application-default login
```
If `gcloud` is not installed, see the [Google Cloud CLI docs](https://cloud.google.com/sdk/docs/install).
