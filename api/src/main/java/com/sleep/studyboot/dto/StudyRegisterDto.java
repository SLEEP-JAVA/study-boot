package com.sleep.studyboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sleep.studyboot.core.study.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class StudyRegisterDto {

    private String name;
    private Category category;
    private String description;
    private String place;
    private int capacity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Builder
    public StudyRegisterDto(String name, Category category, String description, String place, int capacity, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
