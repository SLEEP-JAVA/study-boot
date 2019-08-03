package com.sleep.studyboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sleep.studyboot.core.study.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
class StudyDto {

    @JsonIgnore
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected Long id;
    protected String name;
    protected Category category;
    protected String description;
    protected String place;
    protected int capacity;
    protected String startDate;
    protected String endDate;
}
