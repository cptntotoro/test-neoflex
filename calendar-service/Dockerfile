FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /workspace
COPY . .
RUN mvn -f calendar-service/pom.xml clean package -DskipTests

FROM openjdk:11-jre-slim
COPY --from=build /workspace/calendar-service/target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]