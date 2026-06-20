FROM gradle:8.14-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# Usamos 'gradle' directo en lugar de './gradlew' para forzar la versión 8.14
RUN gradle bootJar --no-daemon -x test

FROM eclipse-temurin:21-jre-jammy
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]