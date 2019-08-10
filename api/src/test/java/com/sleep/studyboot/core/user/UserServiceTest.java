package com.sleep.studyboot.core.user;

import com.sleep.studyboot.core.security.OAuth2UserInfo;
import com.sleep.studyboot.dto.user.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService sut;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        sut = new UserService(userRepository);

        user = User.builder()
                .email("email")
                .name("name")
                .avartarUrl("url")
                .build();
    }

    @Test
    @DisplayName("존재하지 않는 사용자는 생성")
    void createUser() {
        // given
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        // when
        UserDto userDto = sut.saveOrUpdate(OAuth2UserInfo.builder()
                .email("email")
                .name("name")
                .avatarUrl("url")
                .build());

        // then
        assertThat(userDto).isEqualToComparingFieldByField(user);
        verify(userRepository).findByEmail(anyString());
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("존재하면 사용자 정보 업데이트")
    void saveUser() {
        // given
        OAuth2UserInfo userInfo = OAuth2UserInfo.builder()
                .email("email")
                .name("updated Name")
                .avatarUrl("updated Url")
                .build();
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(user));

        // when
        UserDto userDto = sut.saveOrUpdate(userInfo);

        // then
        assertThat(userDto).isEqualToComparingFieldByField(userInfo);
        verify(userRepository).findByEmail(anyString());
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    @DisplayName("email을 통해 사용자 정보 받아오기")
    void getUser() {
        // given
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(user));

        // when
        UserDto userDto = sut.getUser(user.getEmail());

        // then
        assertThat(userDto).isEqualToComparingFieldByField(user);
        verify(userRepository).findByEmail(anyString());
    }
}