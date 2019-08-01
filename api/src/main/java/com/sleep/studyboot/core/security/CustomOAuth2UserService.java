package com.sleep.studyboot.core.security;

import com.sleep.studyboot.core.user.UserService;
import com.sleep.studyboot.exception.UnAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserService userService;

    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String name = (String) attributes.get("name");
        String avartarUrl = (String) attributes.get("avartarUrl");
        String email = (String) attributes.get("email");

        if (StringUtils.isEmpty(name) ||
            StringUtils.isEmpty(avartarUrl) ||
            StringUtils.isEmpty(email)) {
            throw new UnAuthenticationException("리소스로부터 전달 받은 회원 정보가 비었습니다.");
        }

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.newInstance(email, name, avartarUrl);

        userService.saveOrUpdate(oAuth2UserInfo);

        return oAuth2UserInfo;
    }

}
