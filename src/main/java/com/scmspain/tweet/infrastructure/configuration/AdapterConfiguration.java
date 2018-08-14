package com.scmspain.tweet.infrastructure.configuration;

import com.scmspain.tweet.infrastructure.controller.adapter.TweetAdapter;
import com.scmspain.tweet.infrastructure.controller.adapter.TweetAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AdapterConfiguration {
    @Bean
    public TweetAdapter getTweetAdapter() {
        return new TweetAdapterImpl();
    }
}
