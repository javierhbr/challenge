FROM openjdk:8-jdk-alpine
LABEL maintainer="javierhbr@gmail.com"
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE=build/libs/gateway-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} gateway-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway-service-0.0.1-SNAPSHOT.jar"]