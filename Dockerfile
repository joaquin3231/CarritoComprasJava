#FROM amazoncorretto:17-alpine-jdk

#COPY target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

#EXPOSE 8080

#ENTRYPOINT [ "java", "-jar", "/app.jar" ]


FROM ubuntu:latest AS build
RUN apt-get update
RUN apt get install openjdk-17-jdk -y
COPY . .

RUN ./gradlew bootJar --no-deamon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]