package com.kibcode.tweety.Repo;

import com.kibcode.tweety.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

    User findByScreenName(String screenName);

}
