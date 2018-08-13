package com.scmspain.tweet.infrastructure.controller.command;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscardTweetCommandDtoTest {

    @Test
    public void shouldGetAndSetProperties() {
        Long id = 1L;
        DiscardTweetCommandDto command = new DiscardTweetCommandDto();

        command.setTweet(id);

        assertThat(command.getTweet()).isEqualTo(id);
    }
}
