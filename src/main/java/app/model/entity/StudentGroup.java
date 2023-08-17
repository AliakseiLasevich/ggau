package app.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "student_groups")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class StudentGroup extends PublicEntity {

    @Column(name = "number")
    private int number;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "studentGroup", cascade = CascadeType.ALL)
    private List<StudentSubgroup> studentSubgroups;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_course_id")
    private StudentCourse studentCourse;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }

    @Override
    public String getPrefix() {
        return "STGRP";
    }
}
