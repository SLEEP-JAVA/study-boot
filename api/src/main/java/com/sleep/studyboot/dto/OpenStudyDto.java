package com.sleep.studyboot.dto;

import com.sleep.studyboot.core.study.Category;
import com.sleep.studyboot.core.study.Study;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
public class OpenStudyDto extends StudyDto {

    private String createdDate;
    private String modifiedDate;

    @Builder
    public OpenStudyDto(Long id, String name, Category category, String description, String place, int volume,
                          OffsetDateTime startDate, OffsetDateTime endDate, OffsetDateTime createdDate, OffsetDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.volume = volume;
        this.startDate = startDate.format(formatter);
        this.endDate = endDate.format(formatter);
        this.createdDate = createdDate.format(formatter);
        this.modifiedDate = modifiedDate.format(formatter);
    }

    public static OpenStudyDto of(Study study) {
        return OpenStudyDto.builder()
                .id(study.getId())
                .name(study.getName())
                .category(study.getCategory())
                .description(study.getDescription())
                .place(study.getPlace())
                .volume(study.getVolume())
                .startDate(study.getStartDate())
                .endDate(study.getEndDate())
                .createdDate(study.getCreatedDate())
                .modifiedDate(study.getModifiedDate())
                .build();
    }
}
