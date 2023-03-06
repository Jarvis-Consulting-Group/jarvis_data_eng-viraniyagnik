FROM openjdk:8-alpine 
COPY target/java_apps-1.0-SNAPSHOT.jar.original /usr/local/app/grep/lib/twitter.jar 
ENTRYPOINT ["java","-jar","/usr/local/app/twitter/lib/twitter.jar"] 
