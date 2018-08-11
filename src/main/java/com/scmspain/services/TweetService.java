package com.scmspain.services;

import com.scmspain.entities.Tweet;
import java.util.List;

public interface TweetService {

  void publishTweet(String publisher, String text);

  Tweet getTweet(Long id);

  List<Tweet> listAllTweets();

  void discardTweet(Long id);

  List<Tweet> listAllDiscardedTweets();
}
