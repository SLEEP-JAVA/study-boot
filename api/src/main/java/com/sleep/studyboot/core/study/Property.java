package com.sleep.studyboot.core.study;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Property {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String value;

    private Property() {
    }

    public Property(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
