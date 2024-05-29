pipeline {
    agent any
    environment {
        SPRING_DATASOURCE_URL = credentials('SPRING_DATASOURCE_URL')
        SPRING_DATASOURCE_USERNAME = credentials('SPRING_DATASOURCE_USERNAME')
        SPRING_DATASOURCE_PASSWORD = credentials('SPRING_DATASOURCE_PASSWORD')
        JWT_SECRET = credentials('JWT_SECRET')
        JWT_EXPIRATION = credentials('JWT_EXPIRATION')
        DOCKER_USERNAME = credentials('DOCKER_USERNAME')
        DOCKER_PASSWORD = credentials('DOCKER_PASSWORD')
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Checkout code') {
            steps {
                git branch: 'dev', url: 'https://github.com/DFanso/spring-boot-jwt-auth-api.git'
            }
        }
        stage('Set up JDK 21') {
            steps {
                sh 'sudo apt-get update && sudo apt-get install -y openjdk-21-jdk'
            }
        }
        stage('Update application.properties') {
            steps {
                sh '''
                    sed -i 's|spring.datasource.url=.*|spring.datasource.url=${SPRING_DATASOURCE_URL}|' src/main/resources/application.properties
                    sed -i 's|spring.datasource.username=.*|spring.datasource.username=${SPRING_DATASOURCE_USERNAME}|' src/main/resources/application.properties
                    sed -i 's|spring.datasource.password=.*|spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}|' src/main/resources/application.properties
                    sed -i 's|jwt.secret=.*|jwt.secret=${JWT_SECRET}|' src/main/resources/application.properties
                    sed -i 's|jwt.expiration=.*|jwt.expiration=${JWT_EXPIRATION}|' src/main/resources/application.properties
                '''
            }
        }
        stage('Build with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build Docker image') {
            steps {
                sh 'docker build -t spring-jwt-auth .'
            }
        }
        stage('Log in to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                        sh 'echo "Logged in to Docker Hub"'
                    }
                }
            }
        }
        stage('Push Docker image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                        sh '''
                            docker tag spring-jwt-auth ${DOCKER_USERNAME}/spring-jwt-auth:latest
                            docker push ${DOCKER_USERNAME}/spring-jwt-auth:latest
                        '''
                    }
                }
            }
        }
    }
    post {
        success {
            script {
                build job: 'deploy', wait: false
            }
        }
    }
}

pipeline {
    agent any
    stages {
        stage('Log in to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                        sh 'echo "Logged in to Docker Hub"'
                    }
                }
            }
        }
        stage('Pull and run Docker image') {
            steps {
                sh '''
                    docker pull ${DOCKER_USERNAME}/spring-jwt-auth:latest
                    docker run -d -p 3000:8080 --name spring-jwt-auth-container ${DOCKER_USERNAME}/spring-jwt-auth:latest
                '''
            }
        }
    }
}