package com.sleep.studyboot.core.study;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.data.annotation.CreatedBy;

import com.sleep.studyboot.core.user.User;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Study {
    @Id
    private Long id;

    private String name;
    private String theme;

    @CreatedBy
    private User leader;

    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    private OffsetDateTime modifiedDate;
    private OffsetDateTime createdDate;
    private OffsetDateTime removedDate;

    @PrePersist
    protected void onCreate(){
        if (id == null) {
            setId(System.currentTimeMillis());
        }
    }

    Study(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Study() {
        new Study(null, null);
    }
}
