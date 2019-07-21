package com.sleep.studyboot.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class StudyListDto {

    private List<OpenStudyDto> open;

    private List<ClosedStudyDto> closed;

    private StudyListDto(List<OpenStudyDto> open, List<ClosedStudyDto> closed) {
        this.open = open;
        this.closed = closed;
    }

    public static StudyListDto of(List<OpenStudyDto> open, List<ClosedStudyDto> closed) {
        return new StudyListDto(open, closed);
    }
}
