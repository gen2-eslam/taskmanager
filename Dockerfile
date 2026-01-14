FROM openjdk:22-jdk

ADD target/taskmanager.jar taskmanager.jar

ENTRYPOINT ["java", "-jar", "taskmanager.jar"]