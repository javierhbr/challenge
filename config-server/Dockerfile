FROM openjdk:8-jdk-alpine
LABEL maintainer="javierhbr@gmail.com"
VOLUME /tmp
EXPOSE 8888
ARG JAR_FILE=build/libs/config-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} config-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/config-0.0.1-SNAPSHOT.jar"]