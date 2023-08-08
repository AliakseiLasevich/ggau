package app.model.entity;

import app.model.entity.interfaces.GeneratedId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "student_subgroups")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class StudentSubgroup extends BaseEntity implements GeneratedId {

    @Column(name = "name")
    private String name;

    @Column(name = "students_count")
    private int studentsCount;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_group_id")
    private StudentGroup studentGroup;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }

    @Override
    public String getPrefix() {
        return "SUBGR";
    }
}
