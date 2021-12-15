job ('ejemplo2-job-DSL'){
	description(' Job DLS de ejemplo ')
  scm{
    git('https://github.com/nsanchezangulo/jenkins.job.parametrizado.git', 'main'){ node ->
      node / gitConfigName('nsanchezan')
      node / gitConfigEmail('nsanchezan@gmail.com')
     }
  }
  parameters {
      stringParam('nombre', defaultValue='nsancheza', description = 'Parametro de cadena para el job boolean')
      choiceParam ('planeta', ['venus','tierra','marte','pluton'])
      booleanParam('agente', false)
  }
  triggers{
      cron ('H/7 * * * *')
  }
  steps{
      shell("bash jobscript.sh")
  }
  publishers{
      mailer('nsanchezangulo@hotmail.com', true , true )
      slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
