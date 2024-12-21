FROM openjdk:22
ARG JAR_FILE=./target/*.jar
EXPOSE 8080
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

# docker build -t cesarsicas/springstore:0.0.1 .