package com.scmspain.tweet.domain.validation;

import com.scmspain.tweet.domain.entities.Tweet;
import java.util.Arrays;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TweetValidatorTest {

  @Test
  public void shouldCallAllInnerValidators() {
    Validator<Tweet> innerValidator1 = mock(Validator.class);
    Validator<Tweet> innerValidator2 = mock(Validator.class);
    Validator<Tweet> validator = new TweetValidator(Arrays.asList(innerValidator1, innerValidator2));

    Tweet tweet = mock(Tweet.class);
    validator.validate(tweet);
    verify(innerValidator1, times(1)).validate(tweet);
    verify(innerValidator2, times(1)).validate(tweet);
  }
}
