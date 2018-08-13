package com.scmspain.tweet.infrastructure.controller.command;

public class DiscardTweetCommandDto {
  private Long tweet;

  public Long getTweet() {
    return tweet;
  }

  public void setTweet(Long tweet) {
    this.tweet = tweet;
  }
}
