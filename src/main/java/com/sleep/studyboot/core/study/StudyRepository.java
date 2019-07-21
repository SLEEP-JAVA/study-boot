package com.sleep.studyboot.core.study;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface StudyRepository extends JpaRepository<Study, Long> {

    List<Study> findByRemovedDateIsNull();
    List<Study> findByRemovedDateIsNotNull();
}
