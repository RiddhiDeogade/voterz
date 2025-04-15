# Use lightweight Java base image
FROM eclipse-temurin:21-jdk-alpine

# Create a directory in the image to hold your app
WORKDIR /app

# Copy your built jar from target folder
COPY target/voterz-0.0.1-SNAPSHOT.jar app.jar

# Optional: expose the port your app uses
EXPOSE 8080

# Wait for MySQL to be ready before starting (optional for safety)
# Add the wait-for-it script if needed, otherwise skip this part.

# Set the startup command
ENTRYPOINT ["java", "-jar", "app.jar"]
