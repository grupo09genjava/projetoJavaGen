FROM ubuntu:latest AS build

RUN apt-get update && apt-get install openjdk-17-jdk -y

WORkDIR /app

COPY . .

RUN apt-get install maven -y && mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /app/target/generation-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]