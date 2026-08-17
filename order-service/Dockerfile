FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY order-service/pom.xml order-service/pom.xml
RUN mvn -pl order-service -am dependency:go-offline

COPY order-service/src order-service/src
RUN mvn -pl order-service -am package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/order-service/target/*.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]
