package com.sleep.studyboot.core.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "avartar_url")
    private String avartarUrl;

    @Builder
    public User(String nickname, String email, String name, String avartarUrl) {
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.avartarUrl = avartarUrl;
    }

    public User updateByGithub(String name, String snsId, String avartarUrl) {
        this.name = name;
        this.nickname = snsId;
        this.avartarUrl = avartarUrl;

        return this;
    }
}
