package com.scmspain.configuration;

import com.scmspain.controller.TweetController;
import com.scmspain.services.TweetService;
import com.scmspain.services.TweetServiceImpl;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class TweetConfiguration {
    @Bean
    public TweetService getTweetService(EntityManager entityManager, MetricWriter metricWriter) {
        return new TweetServiceImpl(entityManager, metricWriter);
    }

    @Bean
    public TweetController getTweetConfiguration(TweetService tweetService) {
        return new TweetController(tweetService);
    }
}
