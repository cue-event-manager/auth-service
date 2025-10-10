FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /workspace

COPY . .

RUN chmod +x ./gradlew || true

RUN ./gradlew clean build -x test --no-daemon -PskipValidation=true


FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=builder /workspace/applications/app-service/build/libs/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
