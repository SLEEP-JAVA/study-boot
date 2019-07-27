package com.sleep.studyboot.core.user;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
    private UserAuth userAuth;

    @Builder
    private User(String nickname, String email, String name, UserAuth userAuth) {
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.userAuth = userAuth;
    }

    public static User signUp(UserAuth userAuth) {
        return User.builder()
                .nickname(userAuth.getSnsId())
                .email(userAuth.getEmail())
                .name(userAuth.getName())
                .userAuth(userAuth)
                .build();
    }
}
