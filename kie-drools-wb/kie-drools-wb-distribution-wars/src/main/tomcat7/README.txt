Installation notes
==================

Configure JEE security for kie-wb on tomcat (with default realm backed by tomcat-users.xml)
-------------------------------------------------------------------------------------------

1. Copy "kie-tomcat-integration" JAR into TOMCAT_HOME/lib (org.kie:kie-tomcat-integration)
2. Copy "JACC" JAR into TOMCAT_HOME/lib (javax.security.jacc:artifactId=javax.security.jacc-api in JBoss Maven Repository)
3. Copy "slf4j-api" JAR into TOMCAT_HOME/lib (org.slf4j:artifactId=slf4j-api in JBoss Maven Repository)
4. Add valve configuration into TOMCAT_HOME/conf/server.xml inside Host element as last valve definition:

   <Valve className="org.kie.integration.tomcat.JACCValve" />

5. Edit TOMCAT_HOME/conf/tomcat-users.xml to include roles and users, make sure there will be 'analyst' or 'admin' roles defined as it's required to be authorized to use kie-wb
