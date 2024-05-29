FROM gradle:8.3-jdk17 as builder
WORKDIR /app
COPY build.gradle settings.gradle ./
RUN gradle build --no-daemon || return 0
COPY src ./src
RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:17-jdk-alpine as PROD
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]