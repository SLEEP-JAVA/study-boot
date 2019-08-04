package com.sleep.studyboot.core.security;

import com.sleep.studyboot.exception.UnAuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;

import java.util.Map;

class OAuth2UserInfoExtractor {
    static OAuth2UserInfo from(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String name = (String) attributes.get("name");
        String avatarUrl = (String) attributes.get("avatar_url");
        String email = (String) attributes.get("email");

        if (StringUtils.isEmpty(name) ||
            StringUtils.isEmpty(avatarUrl) ||
            StringUtils.isEmpty(email)) {

            throw new UnAuthenticationException("리소스로부터 전달 받은 회원 정보가 비었습니다.");
        }

        return OAuth2UserInfo.newInstance(email, name, avatarUrl);
    }
}
