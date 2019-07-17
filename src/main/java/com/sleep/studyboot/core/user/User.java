package com.sleep.studyboot.core.user;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    private Long id;

    private String nickname;
    private String snsId;
}
