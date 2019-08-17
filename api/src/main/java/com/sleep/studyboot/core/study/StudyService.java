package com.sleep.studyboot.core.study;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sleep.studyboot.dto.StudyDto;
import com.sleep.studyboot.dto.StudyRegisterDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    // TODO userId 처리
    public StudyDto create(Long userId, StudyRegisterDto studyRegisterDto) {
        var study = studyRegisterDto.toStudy();

        return StudyDto.of(studyRepository.save(study));
    }

    public List<StudyDto> getAllStudies() {
        var studies = studyRepository.findByRemovedOnIsNullOrderByCreatedOnDesc();
        return studies.stream()
                      .map(StudyDto::of)
                      .collect(Collectors.toList());
    }

    public StudyDto getStudy(Long studyId) {
        var study = studyRepository.findById(studyId)
                                   .orElseThrow(() -> new IllegalArgumentException("스터디 " + studyId + "는 존재하지 않습니다."));

        return StudyDto.of(study);
    }

    public List<StudyDto> getStudiesBy(Category category) {
        return studyRepository.findByRemovedOnIsNullAndCategoryEqualsOrderByCreatedOnDesc(category)
                              .stream()
                              .map(StudyDto::of)
                              .collect(Collectors.toList());
    }

}