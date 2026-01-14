FROM openjdk:22-jdk
COPY  target/*.jar taskmanager.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "taskmanager.jar"]