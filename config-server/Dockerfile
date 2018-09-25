FROM openjdk:8-jdk-alpine
LABEL maintainer="javierhbr@gmail.com"
VOLUME /tmp
EXPOSE 8888
ARG JAR_FILE=build/libs/config-server-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} config-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/config-server-0.0.1-SNAPSHOT.jar"]