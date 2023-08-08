package app.model.entity;

import app.model.entity.interfaces.GeneratedId;
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

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student_courses")
public class StudentCourse extends BaseEntity implements GeneratedId {

    @Column(name = "courseNumber")
    private int courseNumber;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @OneToMany(mappedBy = "studentCourse", cascade = CascadeType.ALL)
    private List<StudentGroup> studentGroups;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }

    @Override
    public String getPrefix() {
        return "STCRS";
    }
}
