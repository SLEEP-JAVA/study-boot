package com.sleep.studyboot.core.study;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class StudyRepositoryTest {

    @Autowired
    StudyRepository sut;


    @Test
    public void doNothingTest() {
        log.info("Everything is Fine!");
    }

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
        sut.save(mayBeStudy);

        // then
        var study = sut.findById(mayBeStudy.getId())
                       .orElse(null);

        assertThat(study).isNotNull();
        assertThat(study.getId()).isEqualTo(mayBeStudy.getId());
        assertThat(study.getName()).isEqualTo(mayBeStudy.getName());
    }

    @Test
    public void 스터디_최신순_list() {
        // given

        var study = StudyFixture.aStudy();
        var study2 = StudyFixture.aStudy();

        sut.save(study);
        sut.save(study2);

        // when

        List<Study> studyList = sut.findByRemovedOnIsNullOrderByCreatedOnDesc();

        assertThat(studyList).isNotNull();
        assertThat(studyList).hasSize(2);
        assertThat(studyList.get(0).getName()).isEqualTo(study2.getName());
        assertThat(studyList.get(0).getCreatedOn()).isAfter(studyList.get(1).getCreatedOn());

    }

    @Test
    public void 스터디_카테고리로_찾기_list() {

        // given
        var study = StudyFixture.aStudy();
        var study2 = StudyFixture.aStudy(Category.BACKEND);

        sut.saveAll(List.of(study, study2));

        // when
        List<Study> studyList = sut.findByRemovedOnIsNullAndCategoryEqualsOrderByCreatedOnDesc(Category.BACKEND);


        // then
        assertThat(studyList).isNotNull();
        assertThat(studyList).hasSize(1);
        assertThat(studyList).extracting("name").containsOnly(study2.getName());
        assertThat(studyList).extracting("category").containsOnly(study2.getCategory());
    }
}
