FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY catalog-service/pom.xml catalog-service/pom.xml
RUN mvn -pl catalog-service -am dependency:go-offline

COPY catalog-service/src catalog-service/src
RUN mvn -pl catalog-service -am package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/catalog-service/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
