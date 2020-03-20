package entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students_group")
@Getter
@Setter
public class StudentGroup {

    @Id
    @Column(name = "id_group")
    private int id;

    @OneToMany(mappedBy = "studentGroup")
    private List<StudentSubgroup> studentSubgroups;

    @ManyToOne
    @JoinColumn(name = "student_course")
    private StudentCourse studentCourse;

}
