FROM eclipse-temurin:25-jdk AS build
WORKDIR /app
COPY . .

RUN chmod +x mvnw
RUN ./mvnw -pl shared,server clean package -DskipTests

FROM eclipse-temurin:25-jdk
WORKDIR /app

COPY --from=build /app/server/target/server-1.0.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
