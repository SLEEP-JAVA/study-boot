package com.sleep.studyboot.core.study;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudyRepositoryTest {

    @Autowired
    StudyRepository studyRepository;

    @Test
    public void 스터디_생성() {
        // given
        var name = "이름";
        var category = Category.FRONTEND;
        var description = "설명";
        var place = "장소";
        var capacity = 4;
        var startDate = LocalDate.now();
        var endDate = startDate.plusMonths(1);

        // when
        var mayBeStudy = new Study(name, category, description, place, capacity, startDate, endDate, null);
        studyRepository.save(mayBeStudy);

        // then
        var study = studyRepository.findById(mayBeStudy.getId())
                .orElse(null);

        assertThat(study).isNotNull();
        assertThat(study.getId()).isEqualTo(mayBeStudy.getId());
        assertThat(study.getName()).isEqualTo(mayBeStudy.getName());
    }
}
