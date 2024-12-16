#FROM amazoncorretto:17-alpine-jdk

#COPY target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

#EXPOSE 8080

#ENTRYPOINT [ "java", "-jar", "/app.jar" ]


FROM ubuntu:latest AS target
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./mvnw spring-boot:run --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=target target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]