FROM openjdk:17-alphine
COPY target/BecomeADeveloper2023Test-0.0.1-SNAPSHOT.jar test-app.jar
ENTRYPOINT ["java","-jar","test-app.jar"]