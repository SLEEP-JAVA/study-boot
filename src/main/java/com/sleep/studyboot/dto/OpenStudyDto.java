package com.sleep.studyboot.dto;

import com.sleep.studyboot.core.study.Study;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
public class OpenStudyDto {

    private Long id;
    private String name;
    private String theme;

    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;

    @Builder
    public OpenStudyDto(Long id, String name, String theme, OffsetDateTime startDate, OffsetDateTime endDate, OffsetDateTime createdDate, OffsetDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
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