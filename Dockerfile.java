FROM maven:3.5.2-jdk-8-alpine as abc
WORKDIR /app
RUN mkdir target
ADD src /app/src
ADD pom.xml /app/pom.xml
#for proxies, uncomment below line and add proxies in settings.xml
# ADD settings.xml /usr/share/maven/conf           
RUN chmod -R 777 target
RUN mvn package

FROM djay921/tomcat-with-fonts:latest
# FROM tomcat:9.0-jre8-alpine
# RUN mkdir /usr/share/fonts
# RUN cd /usr/share/fonts/ && wget https://sourceforge.net/projects/gs-fonts/files/latest/download && tar xvzf download
# RUN rm -rf $CATALINA_HOME/webapps/ROOT
# RUN rm -rf $CATALINA_HOME/webapps/ROOT.war
# RUN rm -rf $CATALINA_HOME/webapps/wizard.war
# RUN chmod 777 -R /usr/local/tomcat/webapps

# RUN mkdir -p $CATALINA_HOME/webapps/ontologydoc

# RUN chmod 777 $CATALINA_HOME/webapps/ontologydoc

# RUN chmod 777 -R /usr/local/tomcat

COPY --from=abc app/target/ROOT.war $CATALINA_HOME/webapps/

EXPOSE 8080

