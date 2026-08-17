FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY pricing-service/pom.xml pricing-service/pom.xml
RUN mvn -pl pricing-service -am dependency:go-offline

COPY pricing-service/src pricing-service/src
RUN mvn -pl pricing-service -am package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/pricing-service/target/*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
