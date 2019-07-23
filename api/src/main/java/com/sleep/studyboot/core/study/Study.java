package com.sleep.studyboot.core.study;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    // FIXME: String -> Theme
    @Column(length = 10, nullable = false)
    private String theme;

    @Column(nullable = false)
    private OffsetDateTime startDate;

    @Column(nullable = false)
    private OffsetDateTime endDate;

    // TODO: Create User Table
//    @CreatedBy
//    private User leader;

    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;
    private OffsetDateTime removedDate;

    @PrePersist
    protected void onCreate() {
        var now = OffsetDateTime.now();

        this.createdDate = now;
        this.modifiedDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedDate = OffsetDateTime.now();
    }

    public Study() {
    }

    @Builder
    protected Study(String name, String theme, OffsetDateTime startDate, OffsetDateTime endDate) {
        this.name = name;
        this.theme = theme;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected void rename(String name) {
        this.name = name;
    }

    protected void changePeriod(OffsetDateTime startDate, OffsetDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected void remove() {
        this.removedDate = OffsetDateTime.now();
    }
}