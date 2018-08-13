package com.scmspain.tweet.infrastructure.controller.adapter;

import com.scmspain.tweet.domain.model.Tweet;
import com.scmspain.tweet.infrastructure.controller.query.TweetQueryDto;
import java.util.ArrayList;
import java.util.List;

public class TweetAdapterImpl implements TweetAdapter {
    @Override
    public Tweet mapFromTweetQueryDto(TweetQueryDto tweetQueryDto) {
        Tweet tweet = new Tweet();
        tweet.setId(tweetQueryDto.getId());
        tweet.setPublisher(tweetQueryDto.getPublisher());
        tweet.setContent(tweetQueryDto.getTweet());
        tweet.setPre2015MigrationStatus(tweetQueryDto.getPre2015MigrationStatus());
        return tweet;
    }

    @Override
    public List<Tweet> mapFromTweetQueryDtoList(List<TweetQueryDto> tweetQueryDtos) {
        List<Tweet> result = new ArrayList<>();
        for (TweetQueryDto tweetQueryDto : tweetQueryDtos) {
            result.add(mapFromTweetQueryDto(tweetQueryDto));
        }
        return result;
    }

    @Override
    public TweetQueryDto mapFromTweet(Tweet tweet) {
        TweetQueryDto tweetQueryDto = new TweetQueryDto();
        tweetQueryDto.setId(tweet.getId());
        tweetQueryDto.setPublisher(tweet.getPublisher());
        tweetQueryDto.setTweet(tweet.getContent());
        tweetQueryDto.setPre2015MigrationStatus(tweet.getPre2015MigrationStatus());
        return tweetQueryDto;
    }

    @Override
    public List<TweetQueryDto> mapFromTweetList(List<Tweet> tweets) {
        List<TweetQueryDto> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            result.add(mapFromTweet(tweet));
        }
        return result;
    }
}
