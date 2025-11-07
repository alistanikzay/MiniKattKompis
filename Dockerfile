# Stage 1: Bygg applikationen
FROM maven:3.9.2-openjdk-23 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Kör applikationen
FROM eclipse-temurin:23-jre

WORKDIR /app
COPY --from=build /app/target/minikattkompis-1.0.0.jar ./minikattkompis.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "minikattkompis.jar"]
