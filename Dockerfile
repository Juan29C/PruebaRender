# Etapa 1: Construcción
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración de Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Descargar dependencias (capa cacheada)
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Compilar la aplicación (saltar tests para acelerar build)
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine

# Instalar tzdata para manejo de zonas horarias
RUN apk add --no-cache tzdata

# Establecer zona horaria
ENV TZ=America/Lima

# Crear usuario no-root para mayor seguridad
RUN addgroup -S spring && adduser -S spring -G spring

# Crear directorios para archivos persistentes
RUN mkdir -p /app/imagenes /app/documentos && \
    chown -R spring:spring /app

USER spring

WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Variables de entorno por defecto (serán sobrescritas por Render)
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando de inicio con opciones de JVM optimizadas
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar"]
