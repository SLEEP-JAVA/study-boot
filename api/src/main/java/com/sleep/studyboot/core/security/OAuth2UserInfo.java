package com.sleep.studyboot.core.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Getter
@ToString
public class OAuth2UserInfo implements OAuth2User, Serializable {
    private final static ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json().build();

    private String nickname;

    private String email;

    private String name;

    private String avartarUrl;

    @Builder
    public OAuth2UserInfo(String nickname, String email, String name, String avartarUrl) {
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.avartarUrl = avartarUrl;
    }

    public static OAuth2UserInfo newInstnace(String nickname, String email, String name, String avartarUrl) {
        return OAuth2UserInfo.builder()
                .nickname(nickname)
                .email(email)
                .name(name)
                .avartarUrl(avartarUrl)
                .build();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getAttributes() {
        return OBJECT_MAPPER.convertValue(this, new TypeReference<Map<String, Object>>() {
        });
    }
}
