
# Evalit

## Deployment

To run the client side:
```bash
  .\mvnw -pl client javafx:run
```

To run the server side:
```bash
  .\mvnw -pl server spring-boot:run
```

If the server side is not compiling, there is a high chance you haven't run
```bash
  gcloud auth application-default login
```

If the terminal does not know what 'gcloud' is, install Google Cloud CLI:
https://dl.google.com/dl/cloudsdk/channels/rapid/GoogleCloudSDKInstaller.exe
