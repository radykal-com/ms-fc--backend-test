package com.scmspain.tweet.infrastructure.configuration;

import com.scmspain.tweet.domain.repository.TweetRepository;
import com.scmspain.tweet.infrastructure.repository.InMemoryTweetRepositoryImpl;
import javax.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
    @Bean
    public TweetRepository getTweetRepository(EntityManager entityManager) {
        return new InMemoryTweetRepositoryImpl(entityManager);
    }
}
