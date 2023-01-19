#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean install

FROM tomcat

COPY ROOT.war /usr/local/tomcat/webapps/

#RUN mv /usr/local/tomcat/webapps/ROOT.war /usr/local/tomcat/webapps/ROOT.war
