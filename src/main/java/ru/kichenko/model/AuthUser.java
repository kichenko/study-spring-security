package ru.kichenko.model;

import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = -5888410515191415685L;

    private User user;

    public AuthUser(User user) {
        super(user.getUserName(), user.getPassword(),
                user.getRoles() == null ? Collections.emptyList()
                        : user.getRoles().stream().map(Role::getName).map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toSet()));
        this.user = user;
    }
}
