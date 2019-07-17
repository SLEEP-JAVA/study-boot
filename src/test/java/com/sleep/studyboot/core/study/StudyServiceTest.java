package com.sleep.studyboot.core.study;

import lombok.val;
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
        val name = "이름";
        val theme = "테마";
        val startDate = OffsetDateTime.now();
        val endDate = startDate.plusMonths(1);

        Study mockStudy = new Study(name, theme, startDate, endDate);
        when(repository.save(any())).thenReturn(mockStudy);

        // when
        val study = sut.create(name, theme, startDate, endDate);

        // then
        assertThat(study.getName()).isEqualTo(mockStudy.getName());
        assertThat(study.getTheme()).isEqualTo(mockStudy.getTheme());
        assertThat(study.getStartDate()).isEqualTo(mockStudy.getStartDate());
        assertThat(study.getEndDate()).isEqualTo(mockStudy.getEndDate());
    }
}