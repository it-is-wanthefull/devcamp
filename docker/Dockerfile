FROM openjdk:17-jdk-slim-buster
WORKDIR /app
ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar
ARG PROFILE
ENV PROFILE ${PROFILE}
ENV PORT ${PORT}
ENTRYPOINT ["java","-Dserver.port=${PORT}","-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]
