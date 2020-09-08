package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "student_groups")
@Data
public class StudentGroup implements Serializable {

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

    @Column(name = "students_count")
    private int studentsCount;

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
