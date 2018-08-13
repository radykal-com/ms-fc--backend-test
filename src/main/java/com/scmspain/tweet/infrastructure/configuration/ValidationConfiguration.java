package com.scmspain.tweet.infrastructure.configuration;

import com.scmspain.tweet.domain.model.Tweet;
import com.scmspain.tweet.domain.validation.TweetLengthValidator;
import com.scmspain.tweet.domain.validation.TweetPublisherValidator;
import com.scmspain.tweet.domain.validation.TweetValidator;
import com.scmspain.tweet.domain.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfiguration {
    @Bean
    public Validator<Tweet> getTweetValidator() {
        return new TweetValidator(getTweetInnerValidators());
    }

    private List<Validator<Tweet>> getTweetInnerValidators() {
        List<Validator<Tweet>> innerValidators = new ArrayList<>();
        innerValidators.add(new TweetPublisherValidator());
        innerValidators.add(new TweetLengthValidator());
        return innerValidators;
    }
}
