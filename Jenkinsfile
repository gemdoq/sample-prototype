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
                git credentialsId: 'github-pat', url: 'https://github.com/gemdoq/sample-prototype.git', branch: 'main'
            }
        }

        stage('Build with Gradle') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t $DOCKER_IMAGE ."
            }
        }

        stage('Deploy Docker Container') {
            steps {
                script {
                    // 기존 컨테이너 삭제 (실패해도 무시)
                    sh 'docker stop $DOCKER_CONTAINER_NAME || true'
                    sh 'docker rm -f $DOCKER_CONTAINER_NAME || true'

                    // 새로운 컨테이너 실행 (포트 사용 중이면 다른 포트로 변경 가능)
                    sh 'docker run -d -p $TOMCAT_PORT:$TOMCAT_PORT --name $DOCKER_CONTAINER_NAME $DOCKER_IMAGE'
                }
            }
        }
    }
}