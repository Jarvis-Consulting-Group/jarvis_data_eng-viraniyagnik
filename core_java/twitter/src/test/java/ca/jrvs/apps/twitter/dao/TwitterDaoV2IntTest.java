package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.v2.TweetV2;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterDaoV2IntTest {

  private static final Logger logger = LoggerFactory.getLogger(TwitterDaoV2IntTest.class);
  private TwitterDaoV2 twitterDaoV2;

  @Before
  public void setUp() throws Exception {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    twitterDaoV2 = new TwitterDaoV2(httpHelper);
  }

  @Test
  public void create() {
    TweetV2 tweetV2 = new TweetV2();

    tweetV2.setText("Hello!");

    TweetV2 responseTweetV2 = twitterDaoV2.create(tweetV2);
    logger.info(responseTweetV2.toString());
    assertEquals(tweetV2.getText(), responseTweetV2.getText());
  }

  @Test
  public void findById() {
    String text = "@take  \uD83D\uDC4B,  Test  \n"
        + "\n"
        + "Hint:";
    String id = "1615637846785990453";

    TweetV2 tweetV2 = twitterDaoV2.findById(id);
    logger.info(tweetV2.toString());
    assertEquals(text, tweetV2.getText());
    assertEquals(id, tweetV2.getId());
  }

  @Test
  public void deleteById() {
    String id = "1620613439106089653";

    TweetV2 tweetV2 = twitterDaoV2.deleteById(id);
    logger.info(tweetV2.toString());
    assertNotNull(tweetV2);
  }
}