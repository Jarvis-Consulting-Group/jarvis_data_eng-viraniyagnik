package ca.jrvs.apps.twitter.spring;



//@Configuration
public class TwitterCLIBean {

    public static void main(String[] args){
        ApplocationContext context = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
        TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
        app.run(args);
    }

    @Bean
    public TwitterCLIApp twitterCLIApp(Controller service){
        return new TwitterCLIApp(service);
    }

    @Bean
    public Controller controller(Service service){
        return new TwitterController(service);
    }

    @Bean
    public Service service(CrdDao dao){
        return new TwitterService(dao);
    }

    @Bean
    public CrdDao crdDao(HttpHelper httpHelper){
        return new TwitterDao(httpHelper);
    }

    @Bean
    HttpHelper helper(){
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");
        return new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    }
}