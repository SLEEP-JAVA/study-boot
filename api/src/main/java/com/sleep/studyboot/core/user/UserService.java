package com.sleep.studyboot.core.user;

import com.sleep.studyboot.core.security.OAuth2UserInfo;
import com.sleep.studyboot.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto saveOrUpdate(OAuth2UserInfo userInfo) {
        return UserDto.from(
                userRepository.findByEmail(userInfo.getEmail())
                    .map(user ->
                            user.updateByGithub(userInfo.getName(), userInfo.getAvatarUrl()))
                    .orElseGet(() -> saveUser(userInfo)));
    }

    public UserDto getUser(String email) {
        return UserDto.from(userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원이 없습니다.")));
    }


    private User saveUser(OAuth2UserInfo userInfo) {
        return userRepository.save(User.builder()
                .name(userInfo.getName())
                .avatarUrl(userInfo.getAvatarUrl())
                .email(userInfo.getEmail())
                .build());
    }


}
