#FROM maven:3.6-alpine as BUILD
#COPY src /usr/app/src
#COPY pom.xml /usr/app
#COPY settings.xml /usr/share/maven/conf
#RUN mvn -f /usr/app/pom.xml clean install -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine

MAINTAINER hanxiaohui <xgmxgmxm@163.com>

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV TZ=Asia/Shanghai

RUN mkdir /app

WORKDIR /app

COPY target/<MODULE>-0.0.1-SNAPSHOT.jar /app/<MODULE>-0.0.1-SNAPSHOT.jar
EXPOSE 10030

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=${env}","-jar","/app/<MODULE>-0.0.1-SNAPSHOT.jar"]