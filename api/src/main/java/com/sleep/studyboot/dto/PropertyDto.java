package com.sleep.studyboot.dto;

import com.sleep.studyboot.core.study.Property;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PropertyDto {

    private String name;
    private String value;

    private PropertyDto() {
    }

    @Builder
    public PropertyDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static PropertyDto of(Property property) {
        return PropertyDto.builder()
                .name(property.getName())
                .value(property.getValue())
                .build();
    }
}