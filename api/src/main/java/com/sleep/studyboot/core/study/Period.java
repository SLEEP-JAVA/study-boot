package com.sleep.studyboot.core.study;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Getter
public class Period {

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    private Period() {
    }

    public Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;

        validate();
    }

    private void validate() {
        if (Objects.isNull(startDate)) {
            throw new IllegalArgumentException("시작일은 반드시 입력해야 합니다.");
        }

        if (Objects.isNull(endDate)) {
            throw new IllegalArgumentException("종료일은 반드시 입력해야 합니다.");
        }

        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("종료일은 시작일보다 앞설 수 없습니다.");
        }
    }
}
