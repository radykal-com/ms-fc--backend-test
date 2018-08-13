package com.scmspain.tweet.domain.validation;

import com.scmspain.tweet.domain.model.Tweet;
import javax.validation.ValidationException;
import org.springframework.util.StringUtils;

public class TweetPublisherValidator implements Validator<Tweet> {
  @Override
  public void validate(Tweet tweet) throws ValidationException {
    if (StringUtils.isEmpty(tweet.getPublisher())) {
      throw new ValidationException("Publisher can't be empty");
    }
  }
}
