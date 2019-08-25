package com.sleep.studyboot.core.security;

import com.sleep.studyboot.core.user.UserService;
import com.sleep.studyboot.dto.user.LoginUser;
import com.sleep.studyboot.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public LoginUser loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = userService.getUser(email);

        return LoginUser.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .avartarUrl(userDto.getAvatarUrl())
                .build();
    }
}
