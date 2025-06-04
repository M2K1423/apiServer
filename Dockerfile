# Stage 1: Build application
FROM maven:3.9.6 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Cài đặt dos2unix để chuyển đổi encoding
RUN apt-get update && apt-get install -y dos2unix

# Chuyển đổi file application.properties để đảm bảo encoding đúng
RUN dos2unix /app/src/main/resources/application.properties

# Chạy Maven build
RUN mvn clean package -DskipTests

# Stage 2: Create runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
