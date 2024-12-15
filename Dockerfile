# Usa una imagen base de OpenJDK 21
FROM openjdk:21-jdk-slim

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR compilado al contenedor
COPY build/libs/githubstreak-0.0.1-SNAPSHOT.jar githubstreak.jar

# Expon el puerto en el que la aplicación va a correr
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "githubstreak.jar"]
