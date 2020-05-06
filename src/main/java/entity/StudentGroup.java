package entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "students_group")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class StudentGroup implements Serializable {

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

    @ColumnDefault("1")
    @Column(name = "active", columnDefinition = "BOOLEAN")
    private boolean active;
}
