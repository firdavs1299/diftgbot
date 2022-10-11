FROM openjdk:11

WORKDIR /app

COPY target/*.jar /app/appdemo.jar
#COPY application.properties /app

ENTRYPOINT ["java", "-Dspring.profiles.active=development", "-jar", "appdemo.jar"]
