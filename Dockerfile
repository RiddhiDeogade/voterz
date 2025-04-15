# Use lightweight Java base image
FROM eclipse-temurin:21-jdk-alpine

# Create a directory in the image to hold your app
WORKDIR /app

# Copy the built jar from the target folder
COPY target/voterz-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app uses
EXPOSE 8080

# Wait for MySQL to be ready before starting the app (if using wait-for-it)
ENTRYPOINT ["java", "-jar", "app.jar"]