package com.sleep.studyboot.dto;

import com.sleep.studyboot.core.study.Category;
import com.sleep.studyboot.core.study.Study;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ClosedStudyDto extends StudyDto {

    private String removedOn;

    @Builder
    public ClosedStudyDto(Long id, String name, Category category, String description, String place, int capacity,
                          LocalDate startDate, LocalDate endDate, Set<PropertyDto> properties,
                          OffsetDateTime removedOn) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
        this.startDate = startDate.format(dateFormatter);
        this.endDate = endDate.format(dateFormatter);
        this.properties = properties;
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
                .properties(study.getProperties().stream()
                        .map(property -> PropertyDto.of(property))
                        .collect(Collectors.toSet()))
                .removedOn(study.getRemovedOn())
                .build();
    }
}
