# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/*.jar app.jar

# Expose the port that the application listens to
EXPOSE 9000

# Specify the command to run your application
CMD ["java", "-jar", "app.jar", "--server.ip=${CUSTOM_SERVER_IP}"]