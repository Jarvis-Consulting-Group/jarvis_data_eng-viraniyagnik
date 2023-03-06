# Introduction
The Core Java Twitter App allows us to post, find, and delete tweets from a command line interface, using the model-view-controller architecture. The controller/service manages inputs and business logic, the model handles objects and data from the Twitter API, and the view displays tweets in JSON format. We utilized Twitter RESTful API V2, Maven for dependencies, and Mockito and JUnit4 for testing. The app was deployed in a Docker container with OAuth keys set as environment variables.


## Components
- TwitterHttpHelper 
- TwitterDao
- TwitterService
- TwitterController

## Models
The Tweet Model is a Data Transfer Object for the DAO, containing basic information like id, text, public metrics, and entities. The properties are populated using the JSON string body from the Twitter API V2 HTTP response. The public metrics and entities are objects with fields such as hashtags, mentions, likes, and retweets.

## Spring
Spring's dependency injection manages the creation and lifecycles of components. The @Component annotation marks these components as managed by Spring, and the @Configuration annotation marks the TwitterCliBean class, outlining dependency relationships. Each component is marked with @Bean in TwitterCliBean.

# Test
The application is tested with JUnit4 and Mockito libraries for unit and integration testing. These libraries are set up in the Maven test scope, and are run during the Maven build command to verify a successful build.


## Deployment
```Dockerfile
FROM openjdk:8-alpine
COPY target/java_apps-1.0-SNAPSHOT.jar /usr/local/app/twitter/lib/twitter.jar
ENTRYPOINT ["java", "-jar", "/usr/local/app/twitter/lib/twitter.jar"]
```

