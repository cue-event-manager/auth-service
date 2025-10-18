FROM gradle:8.5-jdk17-alpine AS build
WORKDIR /app

COPY . .

RUN gradle clean build -x test -x validateStructure --no-daemon --no-configuration-cache

FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

COPY --from=build /app/applications/app-service/build/libs/*.jar app.jar

RUN chown -R appuser:appgroup /app

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70"

USER appuser

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
