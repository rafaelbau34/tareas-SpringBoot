# Use OpenJDK 17
FROM eclipse-temurin:17-jdk-jammy

# 1. Copy the Maven wrapper and pom.xml first
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# 2. Give execute permission to mvnw
RUN chmod +x mvnw

# 3. Download dependencies (now with proper permissions)
RUN ./mvnw dependency:go-offline

# 4. Copy source code
COPY src ./src

# 5. Build the JAR
RUN ./mvnw clean package

# 6. Run the app
ENTRYPOINT ["java", "-jar", "target/tareas-SpringBoot-0.0.1-SNAPSHOT.jar"]