pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "book-management-server"
        DOCKER_TAG = "${DOCKER_IMAGE}:${BUILD_NUMBER}"
        CONTAINER_NAME = "${DOCKER_IMAGE}"
    }

    triggers {
        pollSCM("H/5 * * * *")
    }

    stages {
        stage('Checkout') {
            steps {
                git 'git@github.com:zzyy/BookManagementServer.git'
            }
        }

        stage('Clean') {
            steps {
                sh './gradlew clean '
            }
        }

        stage('Check Code quality') {
            steps {
                sh './gradlew checkstyleMain'
            }
        }

        stage('Unit Test') {
            steps {
                sh './gradlew clean test'
            }
        }

        stage('Build Jar') {
            steps {
                sh './gradlew clean bootJar'
            }
        }

       stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_TAG} ."
            }
        }

        stage('Deploy') {
            steps {
               sh "docker container stop ${CONTAINER_NAME}"
               sh "docker container rm ${CONTAINER_NAME}"
               sh "docker run -d --network=host --name ${CONTAINER_NAME} -p 8080:8080 ${DOCKER_TAG}"
               sh 'docker ps'
            }
        }
    }

    post {
        success {
            // 如果构建成功，发送通知
            emailext body: 'Your Spring Boot application has been successfully built and deployed.',
                     subject: 'BookManagementServer Build Successful',
                     to: 'your@email.com'
        }
        failure {
            // 如果构建失败，发送通知
            emailext body: 'There was a failure while building or deploying your Spring Boot application.',
                     subject: 'BookManagementServer Build Failed',
                     to: 'your@email.com'
        }
    }
}
