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
                        sh 'cd back-car-user-system && mvn clean install'
                    }
                }
                stage('Frontend') {
                    steps {
                        sh 'cd front-car-user-system && npm install'
                    }
                }
            }
        }
        stage('Run Tests') {
            parallel {
                stage('Backend Tests') {
                    steps {
                        sh 'cd back-car-user-system && mvn test'
                    }
                    post {
                        always {
                            junit '**/back-car-user-system/target/surefire-reports/*.xml'
                        }
                    }
                }
                stage('Frontend Tests') {
                    steps {
                        sh 'cd front-car-user-system && npm run test'
                    }
                }
            }
        }
        stage('SonarQube Analysis - Backend') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'cd back-car-user-system && mvn sonar:sonar'
                }
            }
        }
        stage('SonarQube Analysis - Frontend') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                    cd front-car-user-system
                    npm install -g sonar-scanner
                    sonar-scanner \
                      -Dsonar.projectKey=front-car-user-system \
                      -Dsonar.sources=. \
                      -Dsonar.host.url=${SONARQUBE_SERVER} \
                      -Dsonar.login=${SONARQUBE_CREDENTIALS}
                    '''
                }
            }
        }
        stage('Build Frontend') {
            steps {
                sh 'cd front-car-user-system && npm run build'
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
