package com.sleep.studyboot.dto.user;

import com.sleep.studyboot.core.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private String email;
    private String name;
    private String avartarUrl;

    @Builder
    private UserDto(String email, String name, String avartarUrl) {
        this.email = email;
        this.name = name;
        this.avartarUrl = avartarUrl;
    }

    public static UserDto from(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .avartarUrl(user.getAvartarUrl())
                .build();
    }
}
