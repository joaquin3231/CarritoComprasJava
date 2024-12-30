# Usa una imagen de Tomcat compatible con Java 17
FROM tomcat:10.1-jdk17-temurin

# Establece el directorio de trabajo en la carpeta webapps de Tomcat
WORKDIR /usr/local/tomcat/webapps/

# Copia el archivo .war al directorio de despliegue de Tomcat
COPY target/CarritoCompras-0.0.1-SNAPSHOT.war CarritoCompras.war

# Expone el puerto 8080 (predeterminado de Tomcat)
EXPOSE 8080

# Inicia Tomcat
CMD ["catalina.sh", "run"]
