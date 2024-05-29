FROM openjdk:8-jre-slim
WORKDIR /app
COPY target/auth.jar /app/auth.jar
ENTRYPOINT ["java", "-jar", "auth.jar"]
