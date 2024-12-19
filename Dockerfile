FROM amazoncorretto:17-alpine-jdk

COPY target/CarritoCompras-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 10000

ENTRYPOINT [ "java", "-jar", "/app.jar", "--server.port=10000" ]