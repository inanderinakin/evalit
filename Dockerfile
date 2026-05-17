FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Copy the entire project
COPY . .

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Build the shared and server modules (skipping tests for speed in deployment)
RUN ./mvnw -pl shared,server clean package -DskipTests

# Create the final runtime image
FROM eclipse-temurin:25-jdk
WORKDIR /app

# Copy the built jar from the build stage
# Spring Boot Maven plugin creates the executable jar with the version name
COPY --from=build /app/server/target/server-1.0.0-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
