FROM adoptopenjdk/openjdk11:alpine

WORKDIR /opt/server
COPY ./target/devices-0.0.1-SNAPSHOT.jar deviceServise.jar

EXPOSE 8092

ENTRYPOINT ["java", "-jar", "deviceServise.jar"]