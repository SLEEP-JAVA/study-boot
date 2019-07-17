package com.sleep.studyboot.core.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class StudyService {

    private final StudyRepository studyRepository;

    @Autowired
    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    Study create(String name, String theme, OffsetDateTime startDate, OffsetDateTime endDate) {
        //TODO  DTO 받아서 생성하도록 변경
        Study study = new Study(name, theme, startDate, endDate);
        return studyRepository.save(study);
    }
}
