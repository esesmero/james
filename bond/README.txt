

This is the project Bond: The admin console for James.

- Assuming you have installed maven, compile and install it just running:
$ mvn clean install

- Run it in development mode:
$ mvn gwt:run

- Import and run in Eclipse:

 The archetype generates a project ready to be used in eclipse, 
 but before importing it you have to install the following plugins:

    * Google plugin for eclipse (update-site: http://dl.google.com/eclipse/plugin/3.7 or 3.6 or 3.5)
    * Sonatype Maven plugin (update-site: http://m2eclipse.sonatype.org/site/m2e)

 Then you can import the project in your eclipse workspace:

    * File -> Import -> Existing Projects into Workspace 

 You have to configure 'Annotation Processing' in order to validate Request Factory services
    * Right click on the project -> Java Compiler -> Annotation Processing -> Enable -> Enable processing in Editor -> source folder: target/generated/apt
    * Right click on the project -> Java Compiler -> Annotation Processing -> Factory Path -> Add External Jar -> requestfactory-apt-2.5.1.jar

 Finally you should be able to run the project in development mode and to run the gwt test unit.

    * Right click on the project -> Run as -> Web Application
    * Right click on the test class -> Run as -> GWT JUnit Test 

- Although the project has the files .classpath and .project, you could generate them running any 
 of the following commands:

$ mvn eclipse:m2eclipse  (if you like to use m2eclipse)
$ mvn eclipse:eclipse    (to use the project without m2eclipse)

- Package and run in production. Run the following command to produce a .war file

$ mvn clean package -Dmaven.test.skip

 Then you can run the project adding it to a servlet container (tomcat, jetty, glassfish etc)
 o running it from command line:

$ java -jar target/bond-{version}.war

 To indicate the range of ips authorized to use the app run bond with the 'ip.range' parameter:

$ java -Dip.range=10.0.0 -jar target/bond-{version}.war

 To indicate the folder with the james configuration files use the 'james.conf' parameter:

$ java -Djames.conf=/opt/apache-james-3.0-beta4/conf -jar target/bond-{version}.war

 To indicate the JMX server/port running james

$ java -Djames.jmx=127.0.0.1:9999 -jar target/bond-{version}.war
