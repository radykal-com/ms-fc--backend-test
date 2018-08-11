package com.scmspain.configuration;

import com.scmspain.controller.TweetController;
import com.scmspain.entities.Tweet;
import com.scmspain.services.TweetService;
import com.scmspain.services.TweetServiceImpl;
import com.scmspain.validation.TweetLengthValidator;
import com.scmspain.validation.TweetPublisherValidator;
import com.scmspain.validation.TweetValidator;
import com.scmspain.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class TweetConfiguration {
    @Bean
    public TweetService getTweetService(Validator<Tweet> validator, EntityManager entityManager, MetricWriter metricWriter) {
        return new TweetServiceImpl(validator, entityManager, metricWriter);
    }

    @Bean
    public TweetController getTweetConfiguration(TweetService tweetService) {
        return new TweetController(tweetService);
    }

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
