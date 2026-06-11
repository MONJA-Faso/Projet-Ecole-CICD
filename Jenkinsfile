pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')
        NEXUS_CREDS = credentials('nexus-creds')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build & Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Unit Tests') {
            steps {
                sh 'mvn test -Dmaven.test.failure.ignore=true'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        
        stage('Deploy to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-creds', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
                    sh """
                        echo '<settings><servers><server><id>nexus</id><username>${NEXUS_USER}</username><password>${NEXUS_PASS}</password></server></servers></settings>' > settings.xml
                    """
                    sh 'mvn deploy -s settings.xml -DskipTests'
                }
            }
        }
    }
}
