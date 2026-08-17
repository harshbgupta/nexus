FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY notification-service/pom.xml notification-service/pom.xml
RUN mvn -pl notification-service -am dependency:go-offline

COPY notification-service/src notification-service/src
RUN mvn -pl notification-service -am package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/notification-service/target/*.jar app.jar

EXPOSE 8087

ENTRYPOINT ["java", "-jar", "app.jar"]
