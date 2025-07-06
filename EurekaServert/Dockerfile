# Usa la imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Crea el directorio de trabajo
WORKDIR /app

# Copia el jar al contenedor
COPY target/EurekaServert-0.0.1-SNAPSHOT.jar app.jar


# Expone el puerto 8761
EXPOSE 8761

# Ejecuta el jar
ENTRYPOINT ["java", "-jar", "app.jar"]
