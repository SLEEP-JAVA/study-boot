package com.sleep.studyboot.core.study;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "study")
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

    @Embedded
    private Period period;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "property", joinColumns = @JoinColumn(name = "study_id"))
    private Set<Property> properties = new HashSet();

    // TODO: Create User Table
//    @CreatedBy
//    private User leader;

    @Enumerated(value = EnumType.STRING)
    private StudyStatus status = StudyStatus.OPEN;

    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private OffsetDateTime removedOn;

    @PrePersist
    protected void onCreate() {
        var now = OffsetDateTime.now();

        this.createdOn = now;
        this.modifiedOn = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedOn = OffsetDateTime.now();
    }

    private Study() {
    }

    @Builder
    public Study(String name, Category category, String description, String place, int capacity,
                 LocalDate startDate, LocalDate endDate, Set<Property> properties) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
        this.period = new Period(startDate, endDate);
        this.properties = properties;
    }

    protected void rename(String name) {
        this.name = name;
    }

    protected void changePeriod(LocalDate startDate, LocalDate endDate) {
        this.period = new Period(startDate, endDate);
    }

    protected void remove() {
        this.removedOn = OffsetDateTime.now();
    }
}