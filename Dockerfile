FROM eclipse-temurin:17-jdk-jammy

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

ENTRYPOINT ["java", "-jar", "target/tareas-SpringBoot-0.0.1-SNAPSHOT.jar"]