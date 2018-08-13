package com.scmspain.tweet.domain.validation;

import com.scmspain.tweet.domain.entities.Tweet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ValidationException;

public class TweetLengthValidator implements Validator<Tweet> {

  private static Integer MAX_LENGTH = 140;
  private static String LINK_PATTERN = "(https?://[^\\s]+)";

  private Pattern linkPattern;

  public TweetLengthValidator() {
    linkPattern = Pattern.compile(LINK_PATTERN);
  }

  @Override
  public void validate(Tweet tweet)  throws ValidationException {
    if (countChars(tweet.getTweet()) > MAX_LENGTH) {
      throw new ValidationException(String.format("Tweet must not be greater than %s characters", MAX_LENGTH));
    }
  }

  private int countChars(String tweet) {
    int charsCount = tweet.length();
    Matcher matcher = linkPattern.matcher(tweet);
    while (matcher.find()) {
      String found = matcher.group();
      charsCount -= found.length();
    }
    return charsCount;
  }
}
