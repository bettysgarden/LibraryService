FROM adoptopenjdk/maven-openjdk8

WORKDIR /app

# Copy the Maven project file to the container
COPY pom.xml .

# Download the Maven dependencies
RUN mvn dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Build the JAR file
RUN mvn package

# Copy the JAR file to the container
COPY target/Library-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
