package entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sudents_course")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class StudentCourse implements Serializable {

    @Id
    @Column(name = "id_course")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_number")
    private int courseNumber;

    @ManyToOne
    @JoinColumn(name = "speciality")
    private Specialty specialty;

    @OneToMany(mappedBy = "studentCourse")
    private List<StudentGroup> studentGroups;

}
