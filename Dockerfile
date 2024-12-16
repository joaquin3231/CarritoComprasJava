#FROM amazoncorretto:17-alpine-jdk

#COPY target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

#EXPOSE 8080

#ENTRYPOINT [ "java", "-jar", "/app.jar" ]


#FROM ubuntu:latest AS build
#RUN apt-get update
#RUN apt-get install openjdk-17-jdk -y
#COPY . .

#FROM openjdk:17-jdk-slim
#EXPOSE 8080
#COPY --from=build /target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

#ENTRYPOINT [ "java", "-jar", "/app.jar","--server.port=8080" ]

# Etapa 1: Compilación
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]