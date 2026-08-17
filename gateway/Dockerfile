FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY gateway/pom.xml gateway/pom.xml
RUN mvn -pl gateway -am dependency:go-offline

COPY gateway/src gateway/src
RUN mvn -pl gateway -am package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/gateway/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
