# Primera etapa: construcción
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copia solo el POM primero (para cachear dependencias)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente y compila
COPY src ./src
RUN mvn clean package -DskipTests -Dcheckstyle.skip=true -Dspotbugs.skip=true

# Segunda etapa: ejecución
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Variables de entorno para optimizar Java en contenedores (MENOS MEMORIA)
ENV JAVA_OPTS="-Xmx128m -Xms64m -XX:+UseSerialGC -XX:MaxRAM=256m"

COPY --from=builder /app/target/sistema-saber-pro-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]