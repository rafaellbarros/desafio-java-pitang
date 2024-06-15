pipeline {
    agent any
    environment {
        SONARQUBE_SERVER = 'http://sonarqube:9000'
        SONARQUBE_CREDENTIALS = credentials('sonarqube-token')
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
        DOCKER_IMAGE = 'rafaelbarros/desafio-java-pitang'
    }
    stages {
        stage('Clone sources') {
            steps {
                git 'https://github.com/rafaellbarros/desafio-java-pitang.git'
            }
        }
        stage('Install Dependencies') {
            parallel {
                stage('Backend') {
                    steps {
                        dir('back-car-user-system') {
                            sh 'mvn clean install'
                        }
                    }
                }
                stage('Frontend') {
                    steps {
                        dir('front-car-user-system') {
                            sh 'npm install'
                        }
                    }
                }
            }
        }
        stage('Run Tests') {
            parallel {
                stage('Backend Tests') {
                    steps {
                        dir('back-car-user-system') {
                            sh 'mvn test'
                        }
                    }
                    post {
                        always {
                            junit '**/target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Frontend Tests') {
                    steps {
                        dir('front-car-user-system') {
                            sh 'npm run test'
                        }
                    }
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    dir('back-car-user-system') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
        stage('Build Frontend') {
            steps {
                dir('front-car-user-system') {
                    sh 'npm run build'
                }
            }
        }
        stage('Build Docker Images') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:latest")
                }
            }
        }
        stage('Deploy Dev') {
            steps {
                script {
                    docker.withRegistry('', 'dockerhub-credentials') {
                        docker.image("${DOCKER_IMAGE}:latest").push('dev')
                    }
                }
            }
        }
        stage('Deploy Prod') {
            when {
                input {
                    message "Deploy to production?"
                    ok "Deploy"
                }
            }
            steps {
                script {
                    docker.withRegistry('', 'dockerhub-credentials') {
                        docker.image("${DOCKER_IMAGE}:latest").push('prod')
                    }
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
