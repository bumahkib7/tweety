package com.kibcode.tweety.Repo;

import com.kibcode.tweety.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepo extends JpaRepository<Tweet, Integer> {

    List <Tweet> findByTweetUser_ScreenNameOrContentContains(String screenName, String mention);


}
