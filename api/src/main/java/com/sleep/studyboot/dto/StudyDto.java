package com.sleep.studyboot.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sleep.studyboot.core.study.Category;
import com.sleep.studyboot.core.study.Study;
import com.sleep.studyboot.core.study.StudyStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StudyDto {

    @JsonIgnore
    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @JsonIgnore
    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Long id;
    private String name;
    private Category category;
    private String description;
    private String place;
    private int capacity;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<PropertyDto> properties;
    private StudyStatus status;


    @Builder
    public StudyDto(Long id, String name, Category category, String description,
                    String place, int capacity, LocalDate startDate, LocalDate endDate,
                    Set<PropertyDto> properties, StudyStatus status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.properties = properties;
        this.status = status;
    }

    public static StudyDto of(Study study) {
        return StudyDto.builder()
                       .id(study.getId())
                       .name(study.getName())
                       .category(study.getCategory())
                       .description(study.getDescription())
                       .place(study.getPlace())
                       .capacity(study.getCapacity())
                       .startDate(study.getPeriod().getStartDate())
                       .endDate(study.getPeriod().getEndDate())
                       .properties(study.getProperties()
                                        .stream()
                                        .map(PropertyDto::of)
                                        .collect(Collectors.toSet()))
                       .status(study.getStatus())
                       .build();
    }
}
