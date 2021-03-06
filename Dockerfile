FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/*.jar
copy ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]