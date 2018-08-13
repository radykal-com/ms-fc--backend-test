package com.scmspain.tweet.infrastructure.controller.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class TweetQueryDtoTest {

    @Test
    public void shouldBeRepresentedAsJson() throws JSONException, JsonProcessingException {
        Long id = 1L;
        String publisher = "publisher";
        String tweet = "tweet";
        Long pre2015MigrationStatus = 0L;

        TweetQueryDto tweetQueryDto = new TweetQueryDto();
        tweetQueryDto.setId(id);
        tweetQueryDto.setPublisher(publisher);
        tweetQueryDto.setTweet(tweet);
        tweetQueryDto.setPre2015MigrationStatus(pre2015MigrationStatus);

        String expectedJson = new JSONObject()
            .put("id", id)
            .put("publisher", publisher)
            .put("tweet", tweet)
            .put("pre2015MigrationStatus", pre2015MigrationStatus)
            .toString();

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(tweetQueryDto);
        JSONAssert.assertEquals(expectedJson, result, false);
    }
}
