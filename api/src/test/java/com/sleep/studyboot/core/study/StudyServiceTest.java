package com.sleep.studyboot.core.study;

import com.sleep.studyboot.dto.PropertyDto;
import com.sleep.studyboot.dto.StudyDto;
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
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
        var property = new Property("스택", "Java");

        Study mockStudy = new Study(name, category, description, place, capacity, startDate, endDate, Set.of(property));
        ReflectionTestUtils.setField(mockStudy, "createdOn", OffsetDateTime.now());
        ReflectionTestUtils.setField(mockStudy, "modifiedOn", OffsetDateTime.now());

        when(repository.save(any())).thenReturn(mockStudy);

        // when
        var studyRegisterDto = StudyRegisterDto.builder()
                                               .name(name)
                                               .category(category)
                                               .description(description)
                                               .place(place)
                                               .capacity(capacity)
                                               .startDate(startDate)
                                               .endDate(endDate)
                                               .properties(Set.of(PropertyDto.of(property)))
                                               .build();

        var study = sut.create(userId,studyRegisterDto);

        // then
        assertThat(study.getName()).isEqualTo(mockStudy.getName());
        assertThat(study.getCategory()).isEqualTo(mockStudy.getCategory());
        assertThat(study.getStartDate()).isEqualTo(mockStudy.getPeriod().getStartDate().format(dateFormatter));
        assertThat(study.getEndDate()).isEqualTo(mockStudy.getPeriod().getEndDate().format(dateFormatter));
    }

    @Test
    void 스터디_불러오기_ok() {
        // given
        var study = StudyFixture.aStudy();
        var study2 = StudyFixture.aStudy();

        when(repository.findByRemovedOnIsNullOrderByCreatedOnDesc()).thenReturn(List.of(study, study2));

        // when
        List<StudyDto> studyList = sut.getAllStudies();


        // then
        assertThat(studyList).isNotNull();
        assertThat(studyList).hasSize(2);
        assertThat(studyList.get(0).getName()).isEqualTo(study.getName());
        assertThat(studyList.get(0).getDescription()).isEqualTo(study.getDescription());
    }

}