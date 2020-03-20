package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher")
@Getter
@Setter
public class Teacher {

    @Id
    @Column(name = "id_teacher")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cathedra")
    private Cathedra cathedra;

    @ManyToMany
    @JoinTable(name = "discipline_teacher",
            joinColumns = @JoinColumn(name = "id_teacher"),
            inverseJoinColumns = @JoinColumn(name = "id_discipline"))
    private List<Discipline> disciplines;

}
