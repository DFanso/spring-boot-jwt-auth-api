# Use an official JDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Accept an argument for the datasource URL
ARG SPRING_DATASOURCE_URL

# Set the environment variable
ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}

# Copy the project's jar file into the container at /app
COPY target/*.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
