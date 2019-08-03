package com.sleep.studyboot.dto;

import com.sleep.studyboot.core.study.Category;
import com.sleep.studyboot.core.study.Study;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
public class OpenStudyDto extends StudyDto {

    private String createdOn;
    private String modifiedOn;

    @Builder
    public OpenStudyDto(Long id, String name, Category category, String description, String place, int capacity,
                        LocalDate startDate, LocalDate endDate, OffsetDateTime createdOn, OffsetDateTime modifiedOn) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
        this.startDate = startDate.format(dateFormatter);
        this.endDate = endDate.format(dateFormatter);
        this.createdOn = createdOn.format(dateTimeFormatter);
        this.modifiedOn = modifiedOn.format(dateTimeFormatter);
    }

    public static OpenStudyDto of(Study study) {
        return OpenStudyDto.builder()
                .id(study.getId())
                .name(study.getName())
                .category(study.getCategory())
                .description(study.getDescription())
                .place(study.getPlace())
                .capacity(study.getCapacity())
                .startDate(study.getPeriod().getStartDate())
                .endDate(study.getPeriod().getEndDate())
                .createdOn(study.getCreatedOn())
                .modifiedOn(study.getModifiedOn())
                .build();
    }
}
