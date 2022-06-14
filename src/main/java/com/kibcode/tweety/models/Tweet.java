package com.kibcode.tweety.models;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity

@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @CreationTimestamp
    private Timestamp postTime;

    @ManyToOne
    private User tweetUser;


    @NotNull
    private String content;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tweet tweet = (Tweet) o;
        return id != null && Objects.equals(id, tweet.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
