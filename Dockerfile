FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk maven -y
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jdk-alpine

EXPOSE 8080

COPY --from=build /target/techweek-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]