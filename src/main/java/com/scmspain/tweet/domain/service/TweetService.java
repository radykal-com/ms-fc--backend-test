package com.scmspain.tweet.domain.service;

import com.scmspain.tweet.domain.model.Tweet;
import java.util.List;

public interface TweetService {

  void publishTweet(String publisher, String content);

  List<Tweet> listAllPublishedTweets();

  void discardTweet(Long id);

  List<Tweet> listAllDiscardedTweets();
}
