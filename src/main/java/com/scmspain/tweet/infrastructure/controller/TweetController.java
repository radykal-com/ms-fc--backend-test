package com.scmspain.tweet.infrastructure.controller;

import com.scmspain.tweet.infrastructure.controller.adapter.TweetAdapter;
import com.scmspain.tweet.infrastructure.controller.command.DiscardTweetCommandDto;
import com.scmspain.tweet.infrastructure.controller.command.PublishTweetCommandDto;
import com.scmspain.tweet.domain.service.TweetService;
import com.scmspain.tweet.infrastructure.controller.query.TweetQueryDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
public class TweetController {
    private TweetService tweetService;
    private TweetAdapter tweetAdapter;

    public TweetController(TweetService tweetService, TweetAdapter tweetAdapter) {
        this.tweetService = tweetService;
        this.tweetAdapter = tweetAdapter;
    }

    @GetMapping("/tweet")
    public List<TweetQueryDto> listAllTweets() {
        return tweetAdapter.mapFromTweetList(this.tweetService.listAllPublishedTweets());
    }

    @PostMapping("/tweet")
    @ResponseStatus(CREATED)
    public void publishTweet(@RequestBody PublishTweetCommandDto publishTweetCommand) {
        this.tweetService.publishTweet(publishTweetCommand.getPublisher(), publishTweetCommand.getTweet());
    }

    @GetMapping("/discarded")
    public List<TweetQueryDto> listAllDiscardedTweets() {
        return tweetAdapter.mapFromTweetList(this.tweetService.listAllDiscardedTweets());
    }

    @PostMapping("/discarded")
    @ResponseStatus(NO_CONTENT)
    public void discardTweet(@RequestBody DiscardTweetCommandDto discardTweetCommand) {
        this.tweetService.discardTweet(discardTweetCommand.getTweet());
    }
}
