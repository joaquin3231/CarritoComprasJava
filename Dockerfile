# Etapa 1: Compilación del JAR
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app

# Expone el puerto 8080 para Render
EXPOSE 8080

# Copia el JAR desde la etapa de compilación
COPY --from=build /app/target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

# Ejecuta el JAR y toma el puerto desde la variable de entorno PORT
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:8080}", "--server.address=0.0.0.0"]
