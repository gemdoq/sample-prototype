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

        stage('Generate Env File') {
            steps {
                script {
                    // 환경변수 확인
                    echo "TOMCAT_PORT: ${TOMCAT_PORT}"

                    // .env 파일을 생성하고 환경 변수를 추가
                    def envFileContent = ""
                    env.each { key, value ->
                        envFileContent += "${key}=${value}\n"
                    }

                    // .env 파일로 저장
                    writeFile file: '.env', text: envFileContent
                }
            }
        }

       stage('Deploy Docker Container') {
           steps {
               script {
                   // 기존 컨테이너 삭제 (실패해도 무시)
                   sh 'docker stop $DOCKER_CONTAINER_NAME || true'
                   sh 'docker rm -f $DOCKER_CONTAINER_NAME || true'

                   // Docker 컨테이너 실행 (동적으로 생성된 .env 파일을 사용)
                   sh 'docker run -d -p $TOMCAT_PORT:$TOMCAT_PORT --env-file .env --name $DOCKER_CONTAINER_NAME $DOCKER_IMAGE'
               }
           }
       }
    }
}