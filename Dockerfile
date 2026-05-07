# ── Stage 1: Build ──────────────────────────────────────────────
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy Maven wrapper and pom.xml first (layer caching)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline -q

# Copy source and build the JAR
COPY src/ src/

RUN ./mvnw package -DskipTests -q

# ── Stage 2: Run ────────────────────────────────────────────────
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]