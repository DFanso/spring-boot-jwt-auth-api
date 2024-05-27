pipeline {
    agent any

    environment {
        DOCKER_USERNAME = credentials('docker-username')
        DOCKER_PASSWORD = credentials('docker-password')
        VPS_USER = credentials('vps-username')
        VPS_HOST = credentials('vps-host')
        VPS_SSH_KEY = credentials('vps-ssh-key')
        SPRING_DATASOURCE_URL = credentials('SPRING_DATASOURCE_URL')
        SPRING_DATASOURCE_USERNAME = credentials('SPRING_DATASOURCE_USERNAME')
        SPRING_DATASOURCE_PASSWORD = credentials('SPRING_DATASOURCE_PASSWORD')
        JWT_SECRET = credentials('JWT_SECRET')
        JWT_EXPIRATION = credentials('JWT_EXPIRATION')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/spring-boot-jwt-auth.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("spring-jwt-auth:${env.BUILD_ID}").inside {
                        sh 'mvn clean package'
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials-id') {
                        docker.image("spring-jwt-auth:${env.BUILD_ID}").push('latest')
                    }
                }
            }
        }

        stage('Deploy to VPS') {
            steps {
                sshagent(credentials: ['vps-ssh-key']) {
                    sh '''
                    ssh -o StrictHostKeyChecking=no ${VPS_USER}@${VPS_HOST} << EOF
                    docker pull ${DOCKER_USERNAME}/spring-jwt-auth:latest
                    docker stop spring-jwt-auth || true
                    docker rm spring-jwt-auth || true
                    docker run -d --name spring-jwt-auth -p 8080:8080 \
                    -e SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL} \
                    -e SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME} \
                    -e SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD} \
                    -e JWT_SECRET=${JWT_SECRET} \
                    -e JWT_EXPIRATION=${JWT_EXPIRATION} \
                    ${DOCKER_USERNAME}/spring-jwt-auth:latest
                    EOF
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}
