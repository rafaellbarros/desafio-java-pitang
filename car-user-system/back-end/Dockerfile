FROM openjdk:8-jre-slim
WORKDIR /app
COPY target/back-end.jar /app/back-end.jar
ENTRYPOINT ["java", "-jar", "back-end.jar"]