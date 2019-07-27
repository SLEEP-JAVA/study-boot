package com.sleep.studyboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
class StudyDto {

    @JsonIgnore
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected Long id;
    protected String name;
    protected String theme;

    protected String startDate;
    protected String endDate;
}
