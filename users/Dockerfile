FROM adoptopenjdk/openjdk11:alpine

WORKDIR /opt/server
COPY ./target/users-0.0.1-SNAPSHOT.jar userServise.jar

EXPOSE 8091

ENTRYPOINT ["java", "-jar", "userServise.jar"]