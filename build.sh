#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
set -e

# Print commands and their arguments as they are executed.
set -x

# Build the Maven project
echo "Building Maven project..."
mvn clean install

# Build the Docker image
echo "Building Docker image..."
docker build -t spring-jwt-auth .

# Run the Docker container
echo "Running Docker container..."
docker run -d -p 8080:8080 --name spring-jwt-auth-container spring-jwt-auth
