FROM adoptopenjdk/openjdk11:alpine

WORKDIR /opt/server
COPY ./target/attributes-0.0.1-SNAPSHOT.jar attributeServise.jar

EXPOSE 8094

ENTRYPOINT ["java", "-jar", "attributeServise.jar"]