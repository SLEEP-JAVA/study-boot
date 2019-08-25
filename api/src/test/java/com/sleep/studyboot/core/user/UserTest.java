package com.sleep.studyboot.core.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @Test
    @DisplayName("사용자 정보 업데이트")
    void updateUser() {
        // given
        User user = User.builder()
                .name("name")
                .email("email")
                .avatarUrl("url")
                .build();

        // when
        User updatedUser = user.updateByGithub("updated name", "updated url");

        // then
        assertThat(updatedUser.getName()).isEqualTo("updated name");
        assertThat(updatedUser.getAvatarUrl()).isEqualTo("updated url");
    }
}