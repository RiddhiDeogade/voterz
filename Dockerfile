# Use Maven to build the jar first
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Use lightweight JDK image to run the app
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/voterz-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
