FROM openjdk:11-jre-slim-buster
EXPOSE 8080
ARG JAR_FILE=target/base64jrp-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]