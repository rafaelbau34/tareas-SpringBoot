# Use OpenJDK 17
FROM eclipse-temurin:17-jdk-jammy
# Copy the Maven wrapper and pom.xml first (caching optimization)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Download dependencies
RUN ./mvnw dependency:go-offline
# Copy source code
COPY src ./src
# Build the JAR
RUN ./mvnw clean package
# Run the app
ENTRYPOINT ["java", "-jar", "target/tareas-SpringBoot-0.0.1-SNAPSHOT.jar"]