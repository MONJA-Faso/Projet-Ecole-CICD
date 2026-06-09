pipeline {
    agent any

    stages {

        stage('Compile') {
            steps {
                sh 'javac src/*.java'
            }
        }

        stage('Execute') {
            steps {
                sh 'java -cp src Main'
            }
        }
    }
}