package com.sleep.studyboot.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GithubUserDetails {
    private String login;
    private String avatarUrl;
    private String accessToken;
    private String email;
    private String name;
    private long id;
}
