FROM maven:3.9.6 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Install dos2unix for file conversion (if needed)
RUN apt-get update && apt-get install -y dos2unix

# Convert application.properties to ensure correct encoding
RUN dos2unix /app/src/main/resources/application.properties

RUN mvn clean package -DskipTests

# Stage 2: Create runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
