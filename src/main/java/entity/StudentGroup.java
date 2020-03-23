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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy = "studentGroup")
    private List<StudentSubgroup> studentSubgroups;

    @ManyToOne
    @JoinColumn(name = "student_course")
    private StudentCourse studentCourse;

    @ManyToMany
    @JoinTable(name = "lesson_studentsubgroup",
            joinColumns = @JoinColumn(name = "id_subgroup"),
            inverseJoinColumns = @JoinColumn(name = "id_lesson"))
    private List<Lesson> lessons;

}
