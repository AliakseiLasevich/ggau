package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "students_subgroup")
@Getter
@Setter
public class StudentSubgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_studentsubgroup")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "student_group")
    private StudentGroup studentGroup;

    @ManyToMany
    @JoinTable(name = "discipline__studentsubgroup",
            joinColumns = @JoinColumn(name = "id_studentsubgroup"),
            inverseJoinColumns = @JoinColumn(name = "id_discipline"))
    private List<Discipline> disciplines;
}
