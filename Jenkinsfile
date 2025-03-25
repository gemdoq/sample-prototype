pipeline {
    agent any

    environment {
        TOMCAT_PORT = credentials('TOMCAT_PORT')
        DOCKER_IMAGE = credentials('DOCKER_IMAGE')
        DOCKER_CONTAINER_NAME = credentials('DOCKER_CONTAINER_NAME')
    }

    stages {
        stage('Clone Repository') {
            steps {
                git credentialsId: 'github-pat',
                url: 'https://github.com/gemdoq/sample-prototype.git',
                branch: 'main'
            }
        }

        stage('Build with Gradle') {
            steps {
                sh 'chmod +x ./gradlew'
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
                    sh 'docker stop $DOCKER_CONTAINER_NAME || true'
                    sh 'docker rm -f $DOCKER_CONTAINER_NAME || true'

                    sh """
                        docker run -d \
                        -p ${TOMCAT_PORT}:${TOMCAT_PORT} \
                        --name ${DOCKER_CONTAINER_NAME} \
                        ${DOCKER_IMAGE}
                    """
                }
            }
        }
    }
}