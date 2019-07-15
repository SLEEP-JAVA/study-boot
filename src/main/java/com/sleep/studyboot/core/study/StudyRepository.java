package com.sleep.studyboot.core.study;

import org.springframework.data.jpa.repository.JpaRepository;

interface StudyRepository extends JpaRepository<Study, Long> {
}
