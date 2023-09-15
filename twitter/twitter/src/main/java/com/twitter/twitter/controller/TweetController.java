package com.twitter.twitter.controller;

import com.twitter.twitter.entity.AppUser;
import com.twitter.twitter.entity.Tweet;
import com.twitter.twitter.repository.TweetRepo;
import com.twitter.twitter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetController {
    private final TweetRepo tweetRepo;
    private final UserService userService;

    @PostMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable Long id,@RequestBody Tweet tweet){
        tweet=tweetRepo.save(tweet);
        AppUser user = userService.findById(id);
        user.getTweets().add(tweet);
        userService.save(user);
        return ResponseEntity.ok(tweet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        AppUser user = userService.findById(id);
        return ResponseEntity.ok(user.getTweets());
    }
}
