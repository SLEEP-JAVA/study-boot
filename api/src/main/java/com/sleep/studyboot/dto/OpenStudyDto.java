package com.sleep.studyboot.dto;

import com.sleep.studyboot.core.study.Study;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
public class OpenStudyDto extends StudyDto {

    private String createdDate;
    private String modifiedDate;

    @Builder
    public OpenStudyDto(Long id, String name, String theme, OffsetDateTime startDate, OffsetDateTime endDate, OffsetDateTime createdDate, OffsetDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.startDate = startDate.format(formatter);
        this.endDate = endDate.format(formatter);
        this.createdDate = createdDate.format(formatter);
        this.modifiedDate = modifiedDate.format(formatter);
    }

    public static OpenStudyDto of(Study study) {
        return OpenStudyDto.builder()
                .id(study.getId())
                .name(study.getName())
                .theme(study.getTheme())
                .startDate(study.getStartDate())
                .endDate(study.getEndDate())
                .createdDate(study.getCreatedDate())
                .modifiedDate(study.getModifiedDate())
                .build();
    }
}
