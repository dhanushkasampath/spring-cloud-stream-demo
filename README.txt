WHAT IS SPRING CLOUD STREAM?
============================

Spring Cloud Stream is a framework to build scalable event-driven microservices
connected with shared messaging systems.

It provides a flexible programming model built on already established spring framework





IMPLEMENTATION
=============

We have used same set of dependencies in each micro-service

we have used below functional interfaces in below micro-services

Supplier -> apply-credit-card-service
Function -> credit-card-verification-service
Consumer -> credit-card-generation-service








In this tutorial we will do two things.
1. create jar files of above 3 microservices
for that run "mvn clean install -DSkipTests" in all services (supplier/processor/consumer)

2. deploy spring cloud stream data flow server in local machine.

========================================================================================================

Deploy Spring Cloud Data Flow server in local machine.

Download  Spring Cloud Dataflow Server Jar:


wget https://repo.maven.apache.org/maven2/org/springframework/cloud/spring-cloud-dataflow-server/2.11.5/spring-cloud-dataflow-server-2.11.5.jar

wget https://repo.maven.apache.org/maven2/org/springframework/cloud/spring-cloud-dataflow-shell/2.11.5/spring-cloud-dataflow-shell-2.11.5.jar

wget https://repo.maven.apache.org/maven2/org/springframework/cloud/spring-cloud-skipper-server/2.11.5/spring-cloud-skipper-server-2.11.5.jar

I found above download links using below site
https://dataflow.spring.io/docs/installation/local/manual/

Install Spring Cloud DataFlow Server:


java -jar spring-cloud-skipper-server-2.7.1.jar  <-1st run this

java -jar spring-cloud-dataflow-server-2.8.1.jar

java -jar spring-cloud-dataflow-shell-2.8.1.jar


After deploying all these 3 jars, go to url
http://localhost:9393/dashboard



----------------------------------------------------------------------
Then we will see the dashboard for our spring cloud data flow.


1. Now we have to register our appliation in spring cloud data flow server.
2. then create s3
3. deploy our application as a pipeline using streams  



URIS


maven://com.learn.apply-credit-card-service:jar:0.0.1-SNAPSHOT
maven://com.learn.credit-card-verification-service:jar:0.0.1-SNAPSHOT
maven://com.learn.credit-card-generation-service:jar:0.0.1-SNAPSHOT
























