package com.sleep.studyboot.core.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyService {

    private final StudyRepository studyRepository;

    @Autowired
    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    Study create(Long id, String name) {
        //TODO  DTO 받아서 생성하도록 변경
        Study study = new Study(id, name);
        return studyRepository.save(study);
    }
}

