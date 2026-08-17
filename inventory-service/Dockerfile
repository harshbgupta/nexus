FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY inventory-service/pom.xml inventory-service/pom.xml
RUN mvn -pl inventory-service -am dependency:go-offline

COPY inventory-service/src inventory-service/src
RUN mvn -pl inventory-service -am package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/inventory-service/target/*.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]
