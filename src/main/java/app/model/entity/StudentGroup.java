package app.model.entity;

import app.model.Interfaces.EntityInterface;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "student_groups")
@Getter
@Setter
@NoArgsConstructor
public class StudentGroup implements Serializable, EntityInterface {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

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
}
