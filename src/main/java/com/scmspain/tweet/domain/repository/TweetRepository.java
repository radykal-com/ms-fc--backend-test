package com.scmspain.tweet.domain.repository;

import com.scmspain.tweet.domain.model.Tweet;
import java.util.List;

public interface TweetRepository {

  void persist(Tweet tweet);

  Tweet findById(Long id);

  List<Tweet> findAllActive();

  List<Tweet> findAllDiscarded();
}
