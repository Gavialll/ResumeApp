FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

FROM tomcat

COPY target/ROOT-1.0.war /usr/local/tomcat/webapps/

RUN mv /usr/local/tomcat/webapps/ROOT-1.0.war /usr/local/tomcat/webapps/ROOT.war
