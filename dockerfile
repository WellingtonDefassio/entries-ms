FROM openjdk:17-alpine
VOLUME /tmp 
COPY target/entries-ms-0.0.1-SNAPSHOT.jar entries-ms.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/entries-ms.jar"]