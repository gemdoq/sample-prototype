pipeline {
    agent any

    environment {
        // Jenkins에서 관리하는 환경변수 참조
        DOCKER_IMAGE_NAME = credentials('DOCKER_IMAGE_NAME')
        DOCKER_CONTAINER_NAME = credentials('DOCKER_CONTAINER_NAME')
        TOMCAT_PORT = credentials('TOMCAT_PORT')
    }

    stages {
        stage('Clone Code') {
            steps {
                echo 'Cloning the code from GitHub...'
                git url: 'https://github.com/gemdoq/sample-prototype.git', branch: 'main', credentialsId: 'github-token'
            }
        }

        stage('Build JAR') {
            steps {
                echo 'Building the Spring Boot JAR with Gradle...'
                sh './gradlew clean build -x test'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t ${DOCKER_IMAGE_NAME}:latest .'
            }
        }

        stage('Deploy Docker Container') {
            steps {
                echo 'Deploying Docker container...'
                // 기존 컨테이너 중지 및 제거
                sh 'docker stop ${DOCKER_CONTAINER_NAME} || true'
                sh 'docker rm ${DOCKER_CONTAINER_NAME} || true'
                // 새 컨테이너 실행 (환경변수 주입)
                sh """
                    docker run -d --name \${DOCKER_CONTAINER_NAME} \
                    -e SERVER_PORT=\${TOMCAT_PORT} \
                    -p \${TOMCAT_PORT}:\${TOMCAT_PORT} \${DOCKER_IMAGE_NAME}:latest
                """
            }
        }
    }
}