package com.scmspain.tweet.infrastructure.controller.adapter;

import com.scmspain.tweet.domain.model.Tweet;
import com.scmspain.tweet.infrastructure.controller.query.TweetQueryDto;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TweetAdapterImplTest {
    private static Long ID_1 = 1L;
    private static String PUBLISHER_1 = "publisher";
    private static String CONTENT_1 = "tweet";
    private Long PRE_2015_MIGRATION_STATUS_1 = 0L;

    private static Long ID_2 = 2L;
    private static String PUBLISHER_2 = "publisher2";
    private static String CONTENT_2 = "tweet2";
    private Long PRE_2015_MIGRATION_STATUS_2 = 1L;

    private TweetAdapter tweetAdapter;

    @Before
    public void setUp() {
        tweetAdapter = new TweetAdapterImpl();
    }

    @Test
    public void shouldMapFromTweetQueryDto() {
        TweetQueryDto tweetQueryDto = buildTweetQueryDto(ID_1, PUBLISHER_1, CONTENT_1, PRE_2015_MIGRATION_STATUS_1);

        Tweet tweet = tweetAdapter.mapFromTweetQueryDto(tweetQueryDto);

        assertThat(tweet.getId()).isEqualTo(ID_1);
        assertThat(tweet.getPublisher()).isEqualTo(PUBLISHER_1);
        assertThat(tweet.getContent()).isEqualTo(CONTENT_1);
        assertThat(tweet.getPre2015MigrationStatus()).isEqualTo(PRE_2015_MIGRATION_STATUS_1);
    }

    @Test
    public void shouldMapFromTweetDtoList() {
        TweetQueryDto tweetQueryDto1 = buildTweetQueryDto(ID_1, PUBLISHER_1, CONTENT_1, PRE_2015_MIGRATION_STATUS_1);
        TweetQueryDto tweetQueryDto2 = buildTweetQueryDto(ID_2, PUBLISHER_2, CONTENT_2, PRE_2015_MIGRATION_STATUS_2);

        List<Tweet> tweetList = tweetAdapter.mapFromTweetQueryDtoList(Arrays.asList(tweetQueryDto1, tweetQueryDto2));

        assertThat(tweetList.size()).isEqualTo(2);

        Tweet tweet1 = tweetList.get(0);
        assertThat(tweet1.getId()).isEqualTo(ID_1);
        assertThat(tweet1.getPublisher()).isEqualTo(PUBLISHER_1);
        assertThat(tweet1.getContent()).isEqualTo(CONTENT_1);
        assertThat(tweet1.getPre2015MigrationStatus()).isEqualTo(PRE_2015_MIGRATION_STATUS_1);

        Tweet tweet2 = tweetList.get(1);
        assertThat(tweet2.getId()).isEqualTo(ID_2);
        assertThat(tweet2.getPublisher()).isEqualTo(PUBLISHER_2);
        assertThat(tweet2.getContent()).isEqualTo(CONTENT_2);
        assertThat(tweet2.getPre2015MigrationStatus()).isEqualTo(PRE_2015_MIGRATION_STATUS_2);
    }

    @Test
    public void shouldMapFromTweet() {
        Tweet tweet = buildTweet(ID_1, PUBLISHER_1, CONTENT_1, PRE_2015_MIGRATION_STATUS_1);

        TweetQueryDto tweetQueryDto = tweetAdapter.mapFromTweet(tweet);

        assertThat(tweetQueryDto.getId()).isEqualTo(ID_1);
        assertThat(tweetQueryDto.getPublisher()).isEqualTo(PUBLISHER_1);
        assertThat(tweetQueryDto.getTweet()).isEqualTo(CONTENT_1);
        assertThat(tweetQueryDto.getPre2015MigrationStatus()).isEqualTo(PRE_2015_MIGRATION_STATUS_1);

    }

    @Test
    public void shouldMapFromTweetList() {
        Tweet tweet1 = buildTweet(ID_1, PUBLISHER_1, CONTENT_1, PRE_2015_MIGRATION_STATUS_1);
        Tweet tweet2 = buildTweet(ID_2, PUBLISHER_2, CONTENT_2, PRE_2015_MIGRATION_STATUS_2);
        
        List<TweetQueryDto> tweetQueryDtoList = tweetAdapter.mapFromTweetList(Arrays.asList(tweet1, tweet2));

        assertThat(tweetQueryDtoList.size()).isEqualTo(2);

        TweetQueryDto tweetQueryDto1 = tweetQueryDtoList.get(0);
        assertThat(tweetQueryDto1.getId()).isEqualTo(ID_1);
        assertThat(tweetQueryDto1.getPublisher()).isEqualTo(PUBLISHER_1);
        assertThat(tweetQueryDto1.getTweet()).isEqualTo(CONTENT_1);
        assertThat(tweetQueryDto1.getPre2015MigrationStatus()).isEqualTo(PRE_2015_MIGRATION_STATUS_1);

        TweetQueryDto tweetQueryDto2 = tweetQueryDtoList.get(1);
        assertThat(tweetQueryDto2.getId()).isEqualTo(ID_2);
        assertThat(tweetQueryDto2.getPublisher()).isEqualTo(PUBLISHER_2);
        assertThat(tweetQueryDto2.getTweet()).isEqualTo(CONTENT_2);
        assertThat(tweetQueryDto2.getPre2015MigrationStatus()).isEqualTo(PRE_2015_MIGRATION_STATUS_2);
        
    }

    private TweetQueryDto buildTweetQueryDto(Long id, String publisher, String tweet, Long pre2015MigrationStatus) {
        TweetQueryDto tweetQueryDto = new TweetQueryDto();
        tweetQueryDto.setId(id);
        tweetQueryDto.setPublisher(publisher);
        tweetQueryDto.setTweet(tweet);
        tweetQueryDto.setPre2015MigrationStatus(pre2015MigrationStatus);
        return tweetQueryDto;
    }

    private Tweet buildTweet(Long id, String publisher, String content, Long pre2015MigrationStatus) {
        Tweet tweet = new Tweet();
        tweet.setId(id);
        tweet.setPublisher(publisher);
        tweet.setContent(content);
        tweet.setPre2015MigrationStatus(pre2015MigrationStatus);
        return tweet;
    }
}
