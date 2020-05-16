package entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "students_subgroup")
@Getter
@Setter

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class StudentSubgroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_studentsubgroup")
    private Long id;

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


    @Column(name = "active")
    private boolean active;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}
