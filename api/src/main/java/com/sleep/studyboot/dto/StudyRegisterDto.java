package com.sleep.studyboot.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sleep.studyboot.core.study.Category;
import com.sleep.studyboot.core.study.Property;
import com.sleep.studyboot.core.study.Study;
import com.sleep.studyboot.core.study.StudyStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private Set<PropertyDto> properties = new HashSet();

    private StudyStatus status = StudyStatus.OPEN;

    @Builder
    public StudyRegisterDto(String name, Category category, String description, String place, int capacity,
                            LocalDate startDate, LocalDate endDate, Set<PropertyDto> properties) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.properties = properties;
    }

    public Study toStudy() {
        return Study.builder()
                .name(name)
                .category(category)
                .description(description)
                .place(place)
                .capacity(capacity)
                .startDate(startDate)
                .endDate(endDate)
                .properties(properties.stream()
                        .map(property -> new Property(property.getName(), property.getValue()))
                        .collect(Collectors.toSet()))
                .build();
    }
}
