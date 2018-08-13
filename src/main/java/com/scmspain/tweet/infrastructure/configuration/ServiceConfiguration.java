package com.scmspain.tweet.infrastructure.configuration;

import com.scmspain.tweet.domain.model.Tweet;
import com.scmspain.tweet.domain.repository.TweetRepository;
import com.scmspain.tweet.domain.service.TweetService;
import com.scmspain.tweet.domain.service.TweetServiceImpl;
import com.scmspain.tweet.domain.validation.Validator;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public TweetService getTweetService(TweetRepository repository, Validator<Tweet> validator,
        MetricWriter metricWriter) {
        return new TweetServiceImpl(repository, validator, metricWriter);
    }
}
