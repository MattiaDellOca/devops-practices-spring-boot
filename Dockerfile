FROM amazoncorretto:17

WORKDIR /app

COPY target/accessing-data-jpa-initial-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "accessing-data-jpa-initial-0.0.1-SNAPSHOT.jar"]