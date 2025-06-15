FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# 1. Copy build files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# 2. Download dependencies
RUN ./mvnw dependency:go-offline

# 3. Copy source code
COPY src ./src

# 4. Build (skip tests)
RUN ./mvnw clean package -DskipTests

# 5. Run (using correct JAR name)
ENTRYPOINT ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]