FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY user-service/pom.xml user-service/pom.xml
RUN mvn -pl user-service -am dependency:go-offline

COPY user-service/src user-service/src
RUN mvn -pl user-service -am package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/user-service/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
