# Imagen base ligera para Java 17
FROM openjdk:17-jdk-slim

# Configuración del directorio de trabajo
WORKDIR /app

# Expone el puerto 8080 para Render
EXPOSE 8080

# Copia el archivo .jar generado en el repositorio
COPY target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

# Ejecuta la aplicación con el puerto dinámico de Render
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:8080}", "--server.address=0.0.0.0"]
