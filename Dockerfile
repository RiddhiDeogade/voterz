# Use Maven image to build the project
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a smaller image to run the app
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/voterz-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
