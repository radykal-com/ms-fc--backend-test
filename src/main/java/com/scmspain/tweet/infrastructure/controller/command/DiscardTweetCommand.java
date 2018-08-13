package com.scmspain.tweet.infrastructure.controller.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscardTweetCommand {
  @JsonProperty(value = "tweet")
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
