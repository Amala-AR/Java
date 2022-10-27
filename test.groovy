import jenkins.*
import jenkins.model.* 
import hudson.*
import hudson.model.*

  Object.metaClass.getPropertySafe = 
    { delegate.hasProperty(it)?.getProperty(delegate) }

  def jenkinsCredentials = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
        com.cloudbees.plugins.credentials.Credentials.class,
        Jenkins.instance,
        null,
        null
  );

  for (creds in jenkinsCredentials) {
    print("id: " + creds.id);
    for (attr in ["secret", "username", "password", "description"]) {
      value = creds.getPropertySafe(attr);
      if (value) {
        print(" [" + attr + ":" + value + "] ");
      }
    }
    println("");
  }
