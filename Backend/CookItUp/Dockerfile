FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY CookItUp-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
