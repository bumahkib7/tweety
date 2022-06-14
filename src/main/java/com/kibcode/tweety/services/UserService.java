package com.kibcode.tweety.services;

import com.kibcode.tweety.Repo.UserRepo;
import com.kibcode.tweety.models.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Arrays;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final Scheduler scheduler;

    public UserService(UserRepo userRepo, Scheduler scheduler) {
        this.userRepo = userRepo;
        this.scheduler = scheduler;
    }


    @Transactional(rollbackFor = Exception.class)
    public Mono<User> save(User user) {
        return Mono.fromCallable(()
                        -> userRepo.save(user))
                .publishOn(scheduler);
    }

    public Mono<User> getUserByScreenName(String screenName) {
        return Mono.fromCallable(()
                        -> userRepo.findByScreenName(screenName))
                .publishOn(scheduler);
    }

    public Mono<User> getByUserId(String userId) {
        return Mono.fromCallable(()
                        -> userRepo.findById(userId).get())
                .publishOn(scheduler);
    }

    @Override
    public UserDetails loadUserByUsername(String screenName) throws UsernameNotFoundException {

        User user = userRepo.findByScreenName(screenName);

        if (user == null) {
            throw new UsernameNotFoundException(screenName);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getScreenName(),
                user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole()
                        .toString())));
    }

}
