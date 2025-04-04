# Этап сборки
FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /workspace
COPY . .
RUN mvn -f vacation-service/pom.xml clean package -DskipTests

# Этап запуска
FROM tomcat:9.0-jre11

# Удаляем дефолтные приложения Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Копируем наш WAR
COPY --from=build /workspace/vacation-service/target/ROOT.war /usr/local/tomcat/webapps/

# Ждем пока Tomcat развернет приложение
HEALTHCHECK --interval=10s --timeout=3s --start-period=30s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

EXPOSE 8080
CMD ["catalina.sh", "run"]