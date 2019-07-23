package com.sleep.studyboot.web;

import com.sleep.studyboot.core.study.StudyService;
import com.sleep.studyboot.dto.OpenStudyDto;
import com.sleep.studyboot.dto.StudyListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping("/v1/studies")
    public StudyListDto showStudies() {
        return studyService.getAllStudies();
    }

    @GetMapping("/v1/studies/{studyId}")
    public OpenStudyDto showStudy(@PathVariable Long studyId) {
        return studyService.getStudy(studyId);
    }

    @GetMapping("/v1/users/{userId}/studies")
    public void showStudiesForUser(@PathVariable Long userId) {
//        studyService.getAllStudies(userId);
        throw new UnsupportedOperationException("To be continued...");
    }

    @PostMapping("/v1/users/{userId}/studies")
    public void registerStudy(@PathVariable Long userId) {
//        studyService.setStudy(userId);
        throw new UnsupportedOperationException("To be continued...");
    }
}
