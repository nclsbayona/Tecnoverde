pipeline {
  agent {
    label 'compose-enabled'
  }
  stages {
    stage("Verify tooling") {
       steps{
         sh '''
          docker version
          docker info
          docker-compose version
          curl --version
          '''
       }
    }
    stage("Inform Status I") {
      steps{
        sh 'echo VerifiedI'
      }
    }
    stage("Down docker data") {
      steps {
        sh 'docker-compose -f compose-prod.yml down'
      }
    }
    stage("Prune docker data") {
      steps {
        sh 'docker system prune -a --volumes -f'
      }
    }
    stage("Inform Status II") {
      steps{
        sh 'echo OldDeleted'
      }
    }
    stage("Start project") {
      steps {
        sh 'docker-compose -f compose-prod.yml pull'
        sh 'docker-compose -f compose-prod.yml up -d'
        sh 'docker-compose -f compose-prod.yml ps'
      }
    }
    stage("Inform Status III") {
      steps{
        sh 'echo Up'
      }
    }
  }
}