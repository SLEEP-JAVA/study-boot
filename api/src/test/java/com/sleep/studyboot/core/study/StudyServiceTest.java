package com.sleep.studyboot.core.study;

import com.sleep.studyboot.dto.StudyRegisterDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    @Mock
    StudyRepository repository;

    @InjectMocks
    StudyService sut;

    @Test
    void study_생성_ok() {
        // given
        var name = "이름";
        var theme = "테마";
        var startDate = OffsetDateTime.now();
        var endDate = startDate.plusMonths(1);

        Study mockStudy = new Study(name, theme, startDate, endDate);
        when(repository.save(any())).thenReturn(mockStudy);

        // when
        var study = sut.create(
                StudyRegisterDto.builder()
                        .name(name)
                        .theme(theme)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build()
        );

        // then
        assertThat(study.getName()).isEqualTo(mockStudy.getName());
        assertThat(study.getTheme()).isEqualTo(mockStudy.getTheme());
        assertThat(study.getStartDate()).isEqualTo(mockStudy.getStartDate());
        assertThat(study.getEndDate()).isEqualTo(mockStudy.getEndDate());
    }
}