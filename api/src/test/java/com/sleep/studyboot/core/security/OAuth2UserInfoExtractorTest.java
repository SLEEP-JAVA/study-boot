package com.sleep.studyboot.core.security;

import com.sleep.studyboot.exception.UnAuthenticationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OAuth2UserInfoExtractorTest {

    private OAuth2User oauth2User;

    @BeforeEach
    void setUp() {
        oauth2User = mock(OAuth2User.class);
    }

    @Test
    @DisplayName("Oauth2User로부터 사용자 정보 추출")
    void extractUserInfo() {
        // given
        Map<String, Object> attributes = Map.of("name", "minzy",
                                                "avatar_url", "url",
                                                "email", "abc@abc.com");
        when(oauth2User.getAttributes())
                .thenReturn(attributes);

        // when
        OAuth2UserInfo userInfo = OAuth2UserInfoExtractor.from(oauth2User);

        // then
        assertThat(userInfo.getName()).isEqualTo("minzy");
        assertThat(userInfo.getAvartarUrl()).isEqualTo("url");
        assertThat(userInfo.getEmail()).isEqualTo("abc@abc.com");
    }

    @Test
    @DisplayName("Oauth2User로부터 사용자 정보 추출시 name이 비었음")
    void extractUserInfoWhenEmptyName() {
        // given
        Map<String, Object> attributes = Map.of("avatar_url", "url",
                                                "email", "abc@abc.com");
        when(oauth2User.getAttributes())
                .thenReturn(attributes);

        // when
        assertThrows(UnAuthenticationException.class,
                () -> OAuth2UserInfoExtractor.from(oauth2User));

    }

    @Test
    @DisplayName("Oauth2User로부터 사용자 정보 추출시 avatar_url이 비었음")
    void extractUserInfoWhenEmptyAvatarUrl() {
        // given
        Map<String, Object> attributes = Map.of("name", "minzy",
                                                "email", "abc@abc.com");
        when(oauth2User.getAttributes())
                .thenReturn(attributes);

        // when
        assertThrows(UnAuthenticationException.class,
                () -> OAuth2UserInfoExtractor.from(oauth2User));

    }

    @Test
    @DisplayName("Oauth2User로부터 사용자 정보 추출시 emaill이 비었음")
    void extractUserInfoWhenEmptyEmail() {
        // given
        Map<String, Object> attributes = Map.of("name", "minzy",
                                                "avatar_url", "url");
        when(oauth2User.getAttributes())
                .thenReturn(attributes);

        // when
        assertThrows(UnAuthenticationException.class,
                () -> OAuth2UserInfoExtractor.from(oauth2User));

    }
}