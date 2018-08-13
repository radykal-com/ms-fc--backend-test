package com.scmspain.tweet.infrastructure.controller.command;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PublishTweetCommandDtoTest {

    @Test
    public void shouldGetAndSetProperties() {
        String publisher = "publisher";
        String tweet = "tweet";

        PublishTweetCommandDto command = new PublishTweetCommandDto();
        command.setPublisher(publisher);
        command.setTweet(tweet);

        assertThat(command.getPublisher()).isEqualTo(publisher);
        assertThat(command.getTweet()).isEqualTo(tweet);
    }
}
