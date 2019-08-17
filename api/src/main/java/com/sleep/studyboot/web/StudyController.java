package com.sleep.studyboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sleep.studyboot.core.study.Category;
import com.sleep.studyboot.core.study.StudyService;
import com.sleep.studyboot.dto.StudyDto;
import com.sleep.studyboot.dto.StudyRegisterDto;

@RestController
public class StudyController {

    private final StudyService studyService;

    @Autowired
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/v1/studies")
    public List<StudyDto> showStudies() {
        return studyService.getAllStudies();
    }

    @GetMapping("/v1/studies/{studyId}")
    public StudyDto showStudy(@PathVariable Long studyId) {
        return studyService.getStudy(studyId);
    }

    // TODO /v1/studies 와 합쳐서 params 여부에 따라 달라지게 하기
    @GetMapping("/v1/studies/filter")
    public List<StudyDto> showStudiesByCategory(@RequestParam Category category) {
        return studyService.getStudiesBy(category);
    }

    @GetMapping("/v1/users/{userId}/studies")
    public void showStudiesForUser(@PathVariable Long userId) {
//        studyService.getAllStudies(userId);
        throw new UnsupportedOperationException("To be continued...");
    }

    // FIXME: Spring Security @김민지
    @PostMapping("/v1/users/{userId}/studies")
    public ResponseEntity registerStudy(@PathVariable Long userId, @RequestBody StudyRegisterDto study) {
        studyService.create(userId, study);
        return ResponseEntity.ok().build();
    }
}