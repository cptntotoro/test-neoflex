# Build stage
FROM maven:3.8.6-openjdk-11 AS build

WORKDIR /app
# Копируем только POM сначала для кэширования зависимостей
COPY pom.xml .
# Скачиваем все зависимости
RUN mvn dependency:go-offline -B

# Копируем исходный код
COPY src ./src

# Собираем приложение
RUN mvn clean package -DskipTests -Dmaven.test.skip=true

# Run stage
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]