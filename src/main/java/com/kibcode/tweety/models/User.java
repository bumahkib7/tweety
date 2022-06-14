package com.kibcode.tweety.models;

import com.kibcode.tweety.constants.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @NotNull
    private String UserId;

    @JsonIgnore
    @NotNull
    private String password;

    @NotNull
    private String screenName;

    @NotNull
    private Role role;

    private String bio;

    private String profilePicture;

    @ElementCollection
    private Set<String> following;
}

