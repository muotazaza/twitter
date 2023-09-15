package com.twitter.twitter.repository;

import com.twitter.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepo extends JpaRepository<Tweet, String> {
}
