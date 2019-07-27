package com.sleep.studyboot.core.study;

public enum Category {

    FRONTEND("프론트엔드"),
    BACKEND("백엔드"),
    ALGORITHM("알고리즘"),
    DESING("디자인");

    String description;

    Category(String description) {
        this.description = description;
    }
}
