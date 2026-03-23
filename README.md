
# Evalit

## Deployment

First time setup (or after deleting your local Maven cache):
```bash
  .\mvnw -N install
  .\mvnw -pl shared -am install -DskipTests
```

If you made changes on the shared folder, run:
```bash
  .\mvnw -pl shared install -DskipTests
```

To run the client side:
```bash
  .\mvnw -pl client -am javafx:run
```

To run the server side:
```bash
  .\mvnw -pl server -am spring-boot:run
```

If the server side is not compiling, there is a high chance you haven't run
```bash
  gcloud auth application-default login
```

If the terminal does not know what 'gcloud' is, install Google Cloud CLI:
https://dl.google.com/dl/cloudsdk/channels/rapid/GoogleCloudSDKInstaller.exe
