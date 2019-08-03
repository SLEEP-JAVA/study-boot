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

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(length = 512)
    private String description;

    @Column(length = 20, nullable = false)
    private String place;

    @Column(nullable = false)
    private int capacity;

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
    public Study(String name, Category category, String description, String place, int capacity, OffsetDateTime startDate, OffsetDateTime endDate) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
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