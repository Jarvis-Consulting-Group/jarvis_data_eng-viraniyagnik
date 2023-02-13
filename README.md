# Java Grep App

## Introduction
The project is a well-structured Java application that utilizes advanced Java features and industry-standard tools for development, testing, and deployment. This project is a Java application that has been developed using the IntelliJ IDEA IDE, with the assistance of Maven for building and managing dependencies. The application uses Java.io for reading and writing to files, with advanced Java features such as Stream API and Lambda functions being utilized to streamline the process. The application is built and packaged into a JAR file using Maven, with dependencies like SLF4J for debug logging and JUnit4 for unit testing also managed by Maven. For deployment, a Docker image was created using a DockerFile, which was then distributed on Docker Hub and run within a Docker container.

## Quick Start
Pull the Docker image from Docker Hub and run a container using that image. The necessary volumes are specified during the container launch to allow the application to deploy and run within the Docker environment.

```
docker run -v `pwd`/data:/data -v `pwd`/log:log yagnikvirani/grep .*Romeo.*Juliet.* /data /log/grep.out
```

## Implementation
the implementation process for this project involved using Java, Maven, and Docker to build, package, and deploy a Java application that can read and write to files.


## Issue
When working with large files in Java, it is crucial to consider the size of the Java Heap. If it is too small, it may cause the OutOfMemoryException to be thrown while reading a large file. During testing, the scenario was observed when the Java Heap was set to 5 MB and the Shakespeare.txt file was between 5.2 MB, resulting in the exception. To overcome this, the file can be divided into smaller parts using the Streams and BufferedReader with a specified buffer size, which would prevent the OutOfMemoryException from happening and allow the application to handle large files efficiently. 

## Test

The Java Grep application underwent manual testing, utilizing dependencies managed by Maven, such as the SLF4J logging package. This was done to observe which files were being read by the listFiles method and to track the matching lines produced by the containsPattern method.

## dockerfile
```
FROM openjdk:8-alpine
COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
ENTRYPOINT ["java", "-jar", "/usr/local/app/grep/lib/grep.jar"]
```


