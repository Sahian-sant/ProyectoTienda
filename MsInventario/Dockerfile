# Usar imagen base con Java 17
FROM openjdk:17-jdk-alpine

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copiar el jar al contenedor
COPY target/MsInventario-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto (ajusta si tu app usa otro puerto)
EXPOSE 8031

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
