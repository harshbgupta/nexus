FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY payment-service/pom.xml payment-service/pom.xml
RUN mvn -pl payment-service -am dependency:go-offline

COPY payment-service/src payment-service/src
RUN mvn -pl payment-service -am package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/payment-service/target/*.jar app.jar

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "app.jar"]
