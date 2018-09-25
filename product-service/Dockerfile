FROM openjdk:8-jdk-alpine
LABEL maintainer="javierhbr@gmail.com"
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=build/libs/product-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} product-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/product-service-0.0.1-SNAPSHOT.jar"]