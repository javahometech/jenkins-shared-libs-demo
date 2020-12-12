def call(tomcatIp,tomcatUsr,finalWarName){
  sh "mv target/*.war target/${finalWarName}.war"
  sshagent(['slave-one']) {
      sh "scp -o StrictHostKeyChecking=no target/${finalWarName}.war ${tomcatUsr}@${tomcatIp}:/opt/tomcat8/webapps"
      sh """
          ssh ${tomcatUsr}@${tomcatIp} /opt/tomcat8/bin/shutdown.sh
          ssh ${tomcatUsr}@${tomcatIp} /opt/tomcat8/bin/startup.sh
      """
  }
}
