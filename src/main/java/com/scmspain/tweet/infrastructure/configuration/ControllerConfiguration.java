package com.scmspain.tweet.infrastructure.configuration;

import com.scmspain.tweet.infrastructure.controller.TweetController;
import com.scmspain.tweet.domain.service.TweetService;
import com.scmspain.tweet.infrastructure.controller.adapter.TweetAdapter;
import com.scmspain.tweet.infrastructure.controller.adapter.TweetAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ControllerConfiguration {
    @Bean
    public TweetController getTweetConfiguration(TweetService tweetService, TweetAdapter tweetAdapter) {
        return new TweetController(tweetService, tweetAdapter);
    }

    @Bean
    public TweetAdapter getTweetAdapter() {
        return new TweetAdapterImpl();
    }
}
