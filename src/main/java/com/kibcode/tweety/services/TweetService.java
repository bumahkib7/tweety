package com.kibcode.tweety.services;

import com.kibcode.tweety.Repo.TweetRepo;
import com.kibcode.tweety.models.Tweet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@Transactional(readOnly = true)
public class TweetService {

    private final TweetRepo tweetRepo;
    private final Scheduler scheduler;

    public TweetService(TweetRepo tweetRepo, Scheduler scheduler) {
        this.tweetRepo = tweetRepo;
        this.scheduler = scheduler;
    }

    @Transactional(rollbackFor = Exception.class)
    public Mono<Tweet> save(Tweet tweet) {
        return Mono.fromCallable(()
                        -> tweetRepo.save(tweet))
                .publishOn((Scheduler) scheduler);
    }

    public Flux<Tweet> findAll() {
        return Flux.fromIterable(tweetRepo.findAll())
                .publishOn((Scheduler) scheduler);
    }

    public Flux<Tweet> getRelevantTweets(String screenName) {
        return Flux.fromIterable(tweetRepo.findByTweetUser_ScreenNameOrContentContains(
                screenName, "@" + screenName))
                .publishOn((Scheduler) scheduler);
    }
}

