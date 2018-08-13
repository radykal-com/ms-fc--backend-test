package com.scmspain;

import com.scmspain.tweet.infrastructure.configuration.InfrastructureConfiguration;
import com.scmspain.tweet.infrastructure.configuration.TweetConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({TweetConfiguration.class, InfrastructureConfiguration.class})
public class MsFcTechTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsFcTechTestApplication.class, args);
    }
}
