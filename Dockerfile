# Use the official Gradle image to build the app
FROM gradle:jdk17 AS build
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test

# Use the official OpenJDK image to run the app
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
