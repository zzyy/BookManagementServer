FROM openjdk:17-jdk

VOLUME /tmp

COPY build/libs/BookManage-0.0.1.jar /app/your-application.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/your-application.jar"]
