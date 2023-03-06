package ca.jrvs.apps.twitter.spring;


@SpringBootApplication(scanBasePackages = "ca.jrvs.apps.twitter")

public class TwitterCLISpringBoot implements CommandLineRunner{

    private TwitterCLIApp app;

    @Autowired
    public TwitterCLISpringBoot(TwitterCLIApp app){this.app = app;}

    public static void main(String[] args){
        SpringApplication app = new SpringApplication(TwitterCLISpringBoot.class);

        //Turn off web
        app.setWebApplicationtype(WebApplication.NONE);
        app.run(args);
    }
    
    public void run(String... args) throws Exception{
        app.run(args);
    }
}