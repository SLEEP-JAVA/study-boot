package com.sleep.studyboot.core.user;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    // FIXME: id가 없어서 임시로 추가
    @Id
    private Long id;

    private String nickname;
    private String snsId;
}
