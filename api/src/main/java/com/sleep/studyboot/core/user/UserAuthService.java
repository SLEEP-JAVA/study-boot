package com.sleep.studyboot.core.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserAuthService {
    private static final String ROLE_USER = "ROLE_USER";
    private final UserService userService;

    public UserAuthService(UserService userService) {
        this.userService = userService;
    }

    public UsernamePasswordAuthenticationToken doAuthentication(UserAuth userAuth) {
        return authenticationToken(userService.getUser(userAuth));
    }

    private UsernamePasswordAuthenticationToken authenticationToken(User user) {
        return new UsernamePasswordAuthenticationToken(user, null, getAuthorities(ROLE_USER));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
