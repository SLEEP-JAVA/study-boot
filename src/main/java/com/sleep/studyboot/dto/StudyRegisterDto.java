package com.sleep.studyboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
public class StudyRegisterDto {

    private String name;
    private String theme;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime endDate;

    @Builder
    public StudyRegisterDto(String name, String theme, OffsetDateTime startDate, OffsetDateTime endDate) {
        this.name = name;
        this.theme = theme;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
