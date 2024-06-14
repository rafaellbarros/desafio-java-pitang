FROM openjdk:8-jre-slim
WORKDIR /app
COPY target/gateway.jar /app/gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]
