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
        stage('clone and clean repo') {
            steps {
                sh 'rm -rf demoic || true'
                sh 'git clone https://gitlab.com/ThourayaLouati/demoic || git clone https://github.com/jglick/simple-maven-project-with-tests.git demoic'
                sh 'mvn clean -f demoic'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test -f demoic -Dmaven.test.failure.ignore=true'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar -f demoic'
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
                sh 'mvn deploy -s settings.xml -f demoic -DskipTests'
            }
        }
    }
}