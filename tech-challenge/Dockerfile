
FROM openjdk:21-jdk-slim as build
RUN apt-get update && apt-get install -y maven

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install  -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app
EXPOSE 8081

CMD ["java", "-jar", "app.jar"]



COPY --from=build app/target/                                                                   90-1.0.jar /app/app.jar
