package com.sleep.studyboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sleep.studyboot.core.study.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor
@Getter
public class StudyRegisterDto {

    private String name;
    private Category category;
    private String description;
    private String place;
    private int capacity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Builder
    public StudyRegisterDto(String name, Category category, String description, String place, int capacity, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public OffsetDateTime getStartDate() {
        return OffsetDateTime.of(startDate, ZoneOffset.UTC);
    }

    public OffsetDateTime getEndDate() {
        return OffsetDateTime.of(endDate, ZoneOffset.UTC);
    }
}
