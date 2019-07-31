package com.sleep.studyboot.core.security;

import com.sleep.studyboot.core.user.User;
import com.sleep.studyboot.core.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String name = (String) attributes.get("name");
        String snsId = (String) attributes.get("snsId");
        String avartarUrl = (String) attributes.get("avartarUrl");
        String email = (String) attributes.get("email");

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.newInstnace(snsId, email, name, avartarUrl);

        userRepository.findByEmail(email)
                .map(user -> user.updateByGithub(name, snsId, avartarUrl))
                .orElseGet(() ->
                        userRepository.save(User.builder()
                                .name(name)
                                .nickname(snsId)
                                .avartarUrl(avartarUrl)
                                .email(email)
                                .build())
                );

        return oAuth2UserInfo;
    }

}
