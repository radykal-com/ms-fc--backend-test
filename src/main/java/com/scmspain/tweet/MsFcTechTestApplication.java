package com.scmspain.tweet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "com.scmspain.tweet.domain",
    "com.scmspain.tweet.infrastructure"
})
public class MsFcTechTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsFcTechTestApplication.class, args);
    }
}
