FROM fabric8/java-jboss-openjdk8-jdk:1.0.13

ENV JAVA_APP_JAR celerio-game-editor-admin.jar
ENV AB_OFF true
ENV JAVA_OPTIONS -Dspring.profiles.active=docker-mode

EXPOSE 8080

COPY celerio-game-editor-admin.jar /app/
