FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/sendmail-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} sendmail-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/sendmail-0.0.1-SNAPSHOT.jar"]