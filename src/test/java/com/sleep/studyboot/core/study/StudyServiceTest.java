package com.sleep.studyboot.core.study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    @Mock
    StudyRepository repository;

    @InjectMocks
    StudyService sut;

    @Test
    void study_생성_ok() {
        // given
        Study mockStudy = new Study(1L, "test");
        when(repository.save(any())).thenReturn(mockStudy);

        // when
        Study study = sut.create(1L, "test");

        // then
        assertThat(study.getId()).isNotNull();
        assertThat(study.getName()).isEqualTo(mockStudy.getName());
    }

}