package com.scmspain.validation;

import com.scmspain.entities.Tweet;
import java.util.List;
import javax.validation.ValidationException;


public class TweetValidator implements Validator<Tweet> {

  private List<Validator<Tweet>> innerValidators;

  public TweetValidator(List<Validator<Tweet>> innerValidators) {
    this.innerValidators = innerValidators;
  }

  @Override
  public void validate(Tweet tweet) throws ValidationException {
    for (Validator<Tweet> validator : innerValidators) {
      validator.validate(tweet);
    }
  }
}
