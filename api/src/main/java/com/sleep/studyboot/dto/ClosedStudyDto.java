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
public class ClosedStudyDto extends StudyDto {

    private String removedOn;

    @Builder
    public ClosedStudyDto(Long id, String name, Category category, String description, String place, int capacity,
                          LocalDate startDate, LocalDate endDate, OffsetDateTime removedOn) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
        this.startDate = startDate.format(dateFormatter);
        this.endDate = endDate.format(dateFormatter);
        this.removedOn = removedOn.format(dateTimeFormatter);
    }

    public static ClosedStudyDto of(Study study) {
        return ClosedStudyDto.builder()
                .id(study.getId())
                .name(study.getName())
                .category(study.getCategory())
                .description(study.getDescription())
                .place(study.getPlace())
                .capacity(study.getCapacity())
                .startDate(study.getPeriod().getStartDate())
                .endDate(study.getPeriod().getEndDate())
                .removedOn(study.getRemovedOn())
                .build();
    }
}
