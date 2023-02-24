
  //  private static String CONSUMER_KEY = System.getenv("6JJY75wR6GQJDBN2OALHEnNLd");
    //private static String CONSUMER_SECRET = System.getenv("MjkZl7ACDdVssK7Z8J2FrUVniavNZneEx2Bj85ircQuEDkmB8u");
    //private static String ACCESS_TOKEN = System.getenv("2837734699-xenJSkzGY6QLJawZPID0YFYyXnfWFHoHUz2NMct");
    //private static String TOKEN_SECRET = System.getenv("VbFqd8z9LV6d2adN0neeqz7kMEAGguuCZWhYYJTCKFOlJ");


  package ca.jrvs.apps.twitter.example;

  import com.google.gdata.util.common.base.PercentEscaper;
  import oauth.signpost.OAuthConsumer;
  import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
  import org.apache.http.HttpResponse;
  import org.apache.http.client.HttpClient;
  import org.apache.http.client.methods.HttpPost;
  import org.apache.http.impl.client.HttpClientBuilder;
  import org.apache.http.util.EntityUtils;

  import java.util.Arrays;

  public class TwitterApiTest {
          private static String CONSUMER_KEY = "6JJY75wR6GQJDBN2OALHEnNLd";
          private static String CONSUMER_SECRET = "MjkZl7ACDdVssK7Z8J2FrUVniavNZneEx2Bj85ircQuEDkmB8u";
          private static String ACCESS_TOKEN = "2837734699-xenJSkzGY6QLJawZPID0YFYyXnfWFHoHUz2NMct";
          private static String TOKEN_SECRET = "VbFqd8z9LV6d2adN0neeqz7kMEAGguuCZWhYYJTCKFOlJ";


          public static void main(String[] args) throws Exception {
              OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
              consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

              String status = "today is a good day";
              PercentEscaper percentEscaper = new PercentEscaper("", false);
              String url = "https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status);
              HttpPost request = new HttpPost(url);

              consumer.sign(request);

              System.out.println("HTTP request headers:");
              Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

              HttpClient httpClient = HttpClientBuilder.create().build();
              HttpResponse response = httpClient.execute(request);
              System.out.println(EntityUtils.toString(response.getEntity()));
      }
  }
