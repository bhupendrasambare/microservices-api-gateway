FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9000
CMD ["java", "-jar", "app.jar", "--custom.server-ip=${CUSTOM_SERVER_IP}"]