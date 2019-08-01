package com.sleep.studyboot.core.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "avartar_url")
    private String avartarUrl;

    @Builder
    public User(String email, String name, String avartarUrl) {
        this.email = email;
        this.name = name;
        this.avartarUrl = avartarUrl;
    }

    public User updateByGithub(String name, String avartarUrl) {
        this.name = name;
        this.avartarUrl = avartarUrl;

        return this;
    }
}
