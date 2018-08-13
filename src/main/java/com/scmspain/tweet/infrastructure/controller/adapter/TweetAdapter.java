package com.scmspain.tweet.infrastructure.controller.adapter;

import com.scmspain.tweet.domain.model.Tweet;
import com.scmspain.tweet.infrastructure.controller.query.TweetQueryDto;
import java.util.List;

public interface TweetAdapter {

    Tweet mapFromTweetQueryDto(TweetQueryDto tweetQueryDto);

    List<Tweet> mapFromTweetQueryDtoList(List<TweetQueryDto> tweetQueryDto);

    TweetQueryDto mapFromTweet(Tweet tweet);

    List<TweetQueryDto> mapFromTweetList(List<Tweet> tweets);

}
