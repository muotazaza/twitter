package com.twitter.twitter.service;

import com.twitter.twitter.repository.TweetRepo;

public class TweetService {
    private final TweetRepo tweetRepo;


    public TweetService(TweetRepo tweetRepo) {
        this.tweetRepo = tweetRepo;
    }
}
