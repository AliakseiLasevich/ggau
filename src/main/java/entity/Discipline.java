package entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "discipline")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Discipline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discipline")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "discipline_teacher",
            joinColumns = @JoinColumn(name = "id_discipline"),
            inverseJoinColumns = @JoinColumn(name = "id_teacher"))
    private List<Teacher> teachers;

    @ManyToMany
    @JoinTable(name = "discipline_cabinet",
            joinColumns = @JoinColumn(name = "id_discipline"),
            inverseJoinColumns = @JoinColumn(name = "id_cabinet"))
    private List<Cabinet> cabinets;


    @ManyToMany
    @JoinTable(name = "discipline__studentsubgroup",
            joinColumns = @JoinColumn(name = "id_discipline"),
            inverseJoinColumns = @JoinColumn(name = "id_studentsubgroup"))
    private List<StudentSubgroup> studentSubgroups;
}
