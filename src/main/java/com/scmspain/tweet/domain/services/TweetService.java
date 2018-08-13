package com.scmspain.tweet.domain.services;

import com.scmspain.tweet.domain.entities.Tweet;
import java.util.List;

public interface TweetService {

  void publishTweet(String publisher, String text);

  Tweet getTweet(Long id);

  List<Tweet> listAllTweets();

  void discardTweet(Long id);

  List<Tweet> listAllDiscardedTweets();
}
