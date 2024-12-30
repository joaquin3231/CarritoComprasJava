FROM amazoncorretto:17-alpine-jdk

COPY target/CarritoCompras-0.0.1-SNAPSHOT.war app.war

EXPOSE 8080

ENTRYPOINT [ "java", "-war", "/app.war", "--server.port=8080" ]