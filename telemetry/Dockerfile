FROM adoptopenjdk/openjdk11:alpine

WORKDIR /opt/server
COPY ./target/telemetry-0.0.1-SNAPSHOT.jar telemetryServise.jar

EXPOSE 8093

ENTRYPOINT ["java", "-jar", "telemetryServise.jar"]