package com.sleep.studyboot.dto;

import com.sleep.studyboot.core.study.Category;
import com.sleep.studyboot.core.study.Study;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
public class ClosedStudyDto extends StudyDto {

    private String removedDate;

    @Builder
    public ClosedStudyDto(Long id, String name, Category category, String description, String place, int capacity,
                          OffsetDateTime startDate, OffsetDateTime endDate, OffsetDateTime removedDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
        this.startDate = startDate.format(formatter);
        this.endDate = endDate.format(formatter);
        this.removedDate = removedDate.format(formatter);
    }

    public static ClosedStudyDto of(Study study) {
        return ClosedStudyDto.builder()
                .id(study.getId())
                .name(study.getName())
                .category(study.getCategory())
                .description(study.getDescription())
                .place(study.getPlace())
                .capacity(study.getCapacity())
                .startDate(study.getStartDate())
                .endDate(study.getEndDate())
                .removedDate(study.getRemovedDate())
                .build();
    }
}
