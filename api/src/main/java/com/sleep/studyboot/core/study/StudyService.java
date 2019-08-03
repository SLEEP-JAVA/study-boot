package com.sleep.studyboot.core.study;

import com.sleep.studyboot.dto.ClosedStudyDto;
import com.sleep.studyboot.dto.OpenStudyDto;
import com.sleep.studyboot.dto.StudyListDto;
import com.sleep.studyboot.dto.StudyRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    public OpenStudyDto create(Long userId, StudyRegisterDto studyRegisterDto) {
        var study = studyRegisterDto.toStudy();

        return OpenStudyDto.of(studyRepository.save(study));
    }

    public StudyListDto getAllStudies() {
        return StudyListDto.of(getAllOpenStudies(), getAllClosedStudies());
    }

    public List<OpenStudyDto> getAllOpenStudies() {
        var studies = studyRepository.findByRemovedOnIsNull();

        return studies.stream()
                .map(study -> OpenStudyDto.of(study))
                .collect(Collectors.toList());
    }

    public List<ClosedStudyDto> getAllClosedStudies() {
        var studies = studyRepository.findByRemovedOnIsNotNull();

        return studies.stream()
                .map(study -> ClosedStudyDto.of(study))
                .collect(Collectors.toList());
    }

    public OpenStudyDto getStudy(Long studyId) {
        var study = studyRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("스터디 " + studyId + "는 존재하지 않습니다."));

        return OpenStudyDto.of(study);
    }
}