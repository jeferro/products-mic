# smaller base image for the runtime environment
FROM openjdk:21-oracle

WORKDIR /app

COPY ./code/build/libs/*.jar app.jar

RUN ls

CMD ["java", "-jar", "app.jar"]
