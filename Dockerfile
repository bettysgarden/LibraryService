FROM maven:3.8.1-openjdk-17-slim

ADD target/Library-0.0.1-SNAPSHOT.war app.war

ENTRYPOINT ["java", "-jar", "app.war"]
