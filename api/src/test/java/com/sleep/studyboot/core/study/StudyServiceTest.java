package com.sleep.studyboot.core.study;

import com.sleep.studyboot.dto.StudyRegisterDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    @Mock
    StudyRepository repository;

    @InjectMocks
    StudyService sut;

    final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    void study_생성_ok() {
        // given
        var userId = 0L;
        var name = "이름";
        var category = Category.FRONTEND;
        var description = "설명";
        var place = "장소";
        var capacity = 4;
        var startDate = LocalDate.now();
        var endDate = startDate.plusMonths(1);

        Study mockStudy = new Study(name, category, description, place, capacity, startDate, endDate);
        ReflectionTestUtils.setField(mockStudy, "createdOn", OffsetDateTime.now());
        ReflectionTestUtils.setField(mockStudy, "modifiedOn", OffsetDateTime.now());

        when(repository.save(any())).thenReturn(mockStudy);

        // when
        var study = sut.create(userId,
                StudyRegisterDto.builder()
                        .name(name)
                        .category(category)
                        .description(description)
                        .place(place)
                        .capacity(capacity)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build()
        );

        // then
        assertThat(study.getName()).isEqualTo(mockStudy.getName());
        assertThat(study.getCategory()).isEqualTo(mockStudy.getCategory());
        assertThat(study.getStartDate()).isEqualTo(mockStudy.getStartDate().format(dateFormatter));
        assertThat(study.getEndDate()).isEqualTo(mockStudy.getEndDate().format(dateFormatter));
    }
}