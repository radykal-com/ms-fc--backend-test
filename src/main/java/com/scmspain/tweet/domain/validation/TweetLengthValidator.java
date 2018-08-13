package com.scmspain.tweet.domain.validation;

import com.scmspain.tweet.domain.model.Tweet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ValidationException;
import org.springframework.util.StringUtils;

public class TweetLengthValidator implements Validator<Tweet> {

  private static Integer MAX_LENGTH = 140;
  private static String LINK_PATTERN = "(https?://[^\\s]+)";

  private Pattern linkPattern;

  public TweetLengthValidator() {
    linkPattern = Pattern.compile(LINK_PATTERN);
  }

  @Override
  public void validate(Tweet tweet)  throws ValidationException {
    if (StringUtils.isEmpty(tweet.getContent())) {
      throw new ValidationException("Tweet can't be empty");
    }
    if (countChars(tweet.getContent()) > MAX_LENGTH) {
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
