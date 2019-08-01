package com.sleep.studyboot.core.user;

import com.sleep.studyboot.core.security.OAuth2UserInfo;
import com.sleep.studyboot.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto saveOrUpdate(OAuth2UserInfo userInfo) {
        return UserDto.from(
                userRepository.findByEmail(userInfo.getEmail())
                    .map(user ->
                            user.updateByGithub(userInfo.getName(), userInfo.getAvartarUrl()))
                    .orElseGet(() -> saveUser(userInfo)));
    }

    private User saveUser(OAuth2UserInfo userInfo) {
        return userRepository.save(User.builder()
                .name(userInfo.getName())
                .avartarUrl(userInfo.getAvartarUrl())
                .email(userInfo.getEmail())
                .build());
    }


}
