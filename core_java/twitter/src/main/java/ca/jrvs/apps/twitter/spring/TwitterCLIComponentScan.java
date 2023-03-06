package ca.jrvs.apps.twitter.spring;
@Configuration
@ComponentScan(value = "ca.jrvs.apps.twitter")

public class TwitterCLIComponentScan {

    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIComponentScan.class);
        TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
        app.run(args);
    }
}