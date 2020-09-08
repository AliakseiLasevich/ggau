package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "student_courses")
public class StudentCourse {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

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
}
