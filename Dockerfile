#FROM amazoncorretto:17-alpine-jdk

#COPY target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

#EXPOSE 8080

#ENTRYPOINT [ "java", "-jar", "/app.jar" ]


FROM openjdk:22-jdk-slim AS build
COPY . .
RUN ./mvnw spring-boot:run

FROM openjdk:22-jdk-slim
EXPOSE 8080
COPY --from=build target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]