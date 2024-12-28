FROM gradle:latest AS builder
WORKDIR /app
COPY build.gradle settings.gradle /app/
COPY src /app/src
RUN gradle build --no-daemon -x test

FROM openjdk:17-jdk-slim
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
