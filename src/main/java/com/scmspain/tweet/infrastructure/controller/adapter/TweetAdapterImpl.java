package com.scmspain.tweet.infrastructure.controller.adapter;

import com.scmspain.tweet.domain.model.Tweet;
import com.scmspain.tweet.infrastructure.controller.query.TweetQueryDto;
import java.util.ArrayList;
import java.util.List;

public class TweetAdapterImpl implements TweetAdapter {
    @Override
    public Tweet mapFromTweetDto(TweetQueryDto tweetDto) {
        Tweet tweet = new Tweet();
        tweet.setId(tweetDto.getId());
        tweet.setPublisher(tweetDto.getPublisher());
        tweet.setContent(tweetDto.getContent());
        tweet.setPre2015MigrationStatus(tweetDto.getPre2015MigrationStatus());
        tweet.setDiscarded(tweetDto.getDiscarded());
        tweet.setDiscardedDate(tweetDto.getDiscardedDate());
        return tweet;
    }

    @Override
    public List<Tweet> mapFromTweetDtoList(List<TweetQueryDto> tweetDtos) {
        List<Tweet> result = new ArrayList<>();
        for (TweetQueryDto tweetDto : tweetDtos) {
            result.add(mapFromTweetDto(tweetDto));
        }
        return result;
    }

    @Override
    public TweetQueryDto mapFromTweet(Tweet tweet) {
        TweetQueryDto tweetDto = new TweetQueryDto();
        tweetDto.setId(tweet.getId());
        tweetDto.setPublisher(tweet.getPublisher());
        tweetDto.setContent(tweet.getContent());
        tweetDto.setPre2015MigrationStatus(tweet.getPre2015MigrationStatus());
        tweetDto.setDiscarded(tweet.getDiscarded());
        tweetDto.setDiscardedDate(tweet.getDiscardedDate());
        return tweetDto;
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
