package com.sleep.studyboot.core.study;

import com.sleep.studyboot.dto.ClosedStudyDto;
import com.sleep.studyboot.dto.OpenStudyDto;
import com.sleep.studyboot.dto.StudyListDto;
import com.sleep.studyboot.dto.StudyRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    protected Study create(StudyRegisterDto studyRegisterDto) {
        val study = Study.builder()
                .name(studyRegisterDto.getName())
                .theme(studyRegisterDto.getTheme())
                .startDate(studyRegisterDto.getStartDate())
                .endDate(studyRegisterDto.getEndDate())
                .build();

        return studyRepository.save(study);
    }

    public StudyListDto getAllStudies() {
        return StudyListDto.of(getAllOpenStudies(), getAllClosedStudies());
    }

    public List<OpenStudyDto> getAllOpenStudies() {
        val studies = studyRepository.findByRemovedDateIsNull();

        return studies.stream()
                .map(study -> OpenStudyDto.of(study))
                .collect(Collectors.toList());
    }

    public List<ClosedStudyDto> getAllClosedStudies() {
        val studies = studyRepository.findByRemovedDateIsNotNull();

        return studies.stream()
                .map(study -> ClosedStudyDto.of(study))
                .collect(Collectors.toList());
    }

    public OpenStudyDto getStudy(Long studyId) {
        val study = studyRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("스터디 " + studyId + "는 존재하지 않습니다."));

        return OpenStudyDto.of(study);
    }
}