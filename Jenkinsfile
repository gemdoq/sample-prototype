pipeline {
    agent any

    environment {
        // Jenkins Credentials에서 환경변수 가져오기
        TOMCAT_PORT = credentials('tomcat-port')
        DOCKER_IMAGE = credentials('docker-image')
        DOCKER_CONTAINER_NAME = credentials('docker-container-name')
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Private 리포지토리 접근을 위한 자격 증명 사용
                git credentialsId: 'github-pat', url: 'https://github.com/YourUsername/spring-boot-cicd.git', branch: 'main'
            }
        }

        stage('Build with Gradle') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Deploy Docker Container') {
            steps {
                // 기존 컨테이너 중지 및 제거
                sh "docker stop ${DOCKER_CONTAINER_NAME} || true"
                sh "docker rm ${DOCKER_CONTAINER_NAME} || true"
                // 새 컨테이너 실행
                sh "docker run -d -p ${TOMCAT_PORT}:${TOMCAT_PORT} --name ${DOCKER_CONTAINER_NAME} ${DOCKER_IMAGE}"
            }
        }
    }
}