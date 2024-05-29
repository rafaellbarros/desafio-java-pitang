FROM openjdk:8-jre-slim
WORKDIR /app
COPY target/discovery.jar /app/discovery.jar
ENTRYPOINT ["java", "-jar", "discovery.jar"]

