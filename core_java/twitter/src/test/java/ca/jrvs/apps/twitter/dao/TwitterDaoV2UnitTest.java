package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.JsonUtil;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.v2.TweetV2;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoV2UnitTest {

  private static final Logger logger = LoggerFactory.getLogger(TwitterDaoV2UnitTest.class);

  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDaoV2 twitterDaoV2;

  @Test
  public void showTweet() throws Exception {

    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      twitterDaoV2.findById("1615637846785990453");
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDaoV2 spyDao = Mockito.spy(twitterDaoV2);
    TweetV2 expectedTweet = JsonUtil.toObjectFromJson(testStr2, TweetV2.class);

    doReturn(expectedTweet).when(spyDao).checkResponseV2(any(), anyInt());
    TweetV2 tweetV2 = spyDao.findById("1615637846785990453");
    assertNotNull(tweetV2);
    assertNotNull(tweetV2.getText());
  }

  @Test
  public void postTweet() throws IOException {

    TweetV2 tempTweet = new TweetV2();
    tempTweet.setText("Hello!");

    when(mockHelper.httpPostV2(isNotNull(), isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      twitterDaoV2.create(tempTweet);
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    when(mockHelper.httpPostV2(isNotNull(), isNotNull())).thenReturn(null);
    TwitterDaoV2 spyDao = Mockito.spy(twitterDaoV2);
    TweetV2 expectedTweet = JsonUtil.toObjectFromJson(testStr2, TweetV2.class);
    doReturn(expectedTweet).when(spyDao).checkResponseV2(any(), anyInt());
    TweetV2 tweetV2 = spyDao.create(tempTweet);
    logger.info(tweetV2.toString());
    assertNotNull(tweetV2);
    assertNotNull(tweetV2.getText());
  }

  @Test
  public void deleteTweet() throws IOException {

    String id = "1615637846785990453";

    when(mockHelper.httpDeleteV2(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      twitterDaoV2.deleteById(id);
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    when(mockHelper.httpDeleteV2(isNotNull())).thenReturn(null);
    TwitterDaoV2 spyDao = Mockito.spy(twitterDaoV2);
    TweetV2 tweetV2 = spyDao.deleteById(id);
    assertNull(tweetV2);
  }

  private static final String testStr2 = "{\n"
      + "     \"id\":\"1615637846785990453\",\n"
      + "     \"text\":\"@take  \uD83D\uDC4B,  Test  \\n\\nHint:\",\n"
      + "     \"created_at\":\"2023-02-27T05:17:40.000Z\",\n"
      + "     \"entities\": {\n"
      + "       \"hashtags\": [{\n"
      + "         \"start\": 8,\n"
      + "         \"end\": 13,\n"
      + "         \"tag\": \"test\"\n"
      + "       }],\n"
      + "       \"mentions\": [{\n"
      + "         \"start\": 0,\n"
      + "         \"end\": 11,\n"
      + "         \"username\": \"take\",\n"
      + "         \"id\":\"1615637846785990453\"\n"
      + "       },\n"
      + "       {\n"
      + "         \"start\": 16,\n"
      + "         \"end\": 27,\n"
      + "         \"username\": \"take\",\n"
      + "         \"id\":\"1615637846785990453\"\n"
      + "       }]\n"
      + "       },\n"
      + "     \"public_metrics\": {\n"
      + "       \"retweet_count\": 1,\n"
      + "       \"reply_count\": 6,\n"
      + "       \"like_count\": 10,\n"
      + "       \"quote_count\": 3,\n"
      + "       \"impression_count\": 3244\n"
      + "     }\n"
      + "   }\n";
}