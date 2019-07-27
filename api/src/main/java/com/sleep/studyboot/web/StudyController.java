package com.sleep.studyboot.web;

import com.sleep.studyboot.core.study.StudyService;
import com.sleep.studyboot.dto.StudyRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping("/v1/studies")
    public ResponseEntity showStudies() {
        var studies = studyService.getAllStudies();
        return ResponseEntity.ok().body(studies);
    }

    @GetMapping("/v1/studies/{studyId}")
    public ResponseEntity showStudy(@PathVariable Long studyId) {
        var study = studyService.getStudy(studyId);
        return ResponseEntity.ok().body(study);
    }

    @GetMapping("/v1/users/{userId}/studies")
    public void showStudiesForUser(@PathVariable Long userId) {
//        studyService.getAllStudies(userId);
        throw new UnsupportedOperationException("To be continued...");
    }

    // FIXME: Spring Security @김민지
    @PostMapping("/v1/users/{userId}/studies")
    public ResponseEntity registerStudy(@PathVariable Long userId, @RequestBody StudyRegisterDto study) {
        studyService.setStudy(userId, study);
        return ResponseEntity.ok().build();
    }
}