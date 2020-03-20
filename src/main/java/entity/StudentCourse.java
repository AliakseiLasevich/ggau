package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sudents_course")
@Getter
@Setter
public class StudentCourse {

    @Id
    @Column(name = "id_course")
    private int id;

    @Column(name = "course_number")
    private int courseNumber;

    @ManyToOne
    @JoinColumn(name = "speciality")
    private Specialty specialty;

    @OneToMany(mappedBy = "studentCourse")
    private List<StudentGroup> studentGroups;

}
