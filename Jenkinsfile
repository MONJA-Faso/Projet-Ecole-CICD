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
                // Récupère le code depuis le dépôt Git configuré dans Jenkins
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
                sh '''
                    cat <<EOF > settings.xml
                    <settings>
                      <servers>
                        <server>
                          <id>nexus</id>
                          <username>${NEXUS_CREDS_USR}</username>
                          <password>${NEXUS_CREDS_PSW}</password>
                        </server>
                      </servers>
                    </settings>
                    EOF
                '''
                sh 'mvn deploy -s settings.xml -DskipTests'
            }
        }
    }
}