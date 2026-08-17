FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY discovery-server/pom.xml discovery-server/pom.xml
RUN mvn -pl discovery-server -am dependency:go-offline

COPY discovery-server/src discovery-server/src
RUN mvn -pl discovery-server -am package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/discovery-server/target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]
