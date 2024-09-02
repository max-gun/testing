FROM openjdk:17-slim-buster as build

WORKDIR /app

COPY gradlew ./
COPY gradle ./gradle

COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

RUN chmod +x ./gradlew
RUN ./gradlew clean build test
FROM openjdk:17-slim-buster
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
