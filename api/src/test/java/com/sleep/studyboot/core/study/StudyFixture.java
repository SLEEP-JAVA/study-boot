package com.sleep.studyboot.core.study;

import java.time.LocalDate;
import java.util.Set;

public class StudyFixture {

    public static Study aStudy() {
        var userId = 0L;
        var name = "이름" + System.currentTimeMillis();
        var category = Category.FRONTEND;
        var description = "설명";
        var place = "장소";
        var capacity = 4;
        var startDate = LocalDate.now();
        var endDate = startDate.plusMonths(1);
        var property = new Property("스택", "Java");

        return new Study(name, category, description, place, capacity, startDate, endDate, Set.of(property));
    }

    public static Study aStudy(Category cate) {
        var userId = 0L;
        var name = "이름" + System.currentTimeMillis();
        var category = cate;
        var description = "설명";
        var place = "장소";
        var capacity = 4;
        var startDate = LocalDate.now();
        var endDate = startDate.plusMonths(1);
        var property = new Property("스택", "Java");

        return new Study(name, category, description, place, capacity, startDate, endDate, Set.of(property));
    }
}
