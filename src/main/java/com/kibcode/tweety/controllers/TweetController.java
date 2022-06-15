package com.kibcode.tweety.controllers;

import com.kibcode.tweety.models.Tweet;
import com.kibcode.tweety.services.TweetService;
import com.kibcode.tweety.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;   // <-- injected
    private final UserService userService; // <-- injected

    public TweetController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @PostMapping
    public Mono<Tweet> save(Principal principal, Tweet tweet) {
        return userService.getUserByScreenName(principal.getName())
                .flatMap(user -> {
                    tweet.setTweetUser(user);
                    return tweetService.save(tweet);
                });
    }

    @GetMapping
    public Flux<Tweet> getAll(Principal principal) {
        return tweetService.getRelevantTweets(principal.getName());
    }
}
