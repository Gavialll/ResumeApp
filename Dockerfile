
# syntax=docker/dockerfile:1

FROM tomcat

COPY target/ResumeServer-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/