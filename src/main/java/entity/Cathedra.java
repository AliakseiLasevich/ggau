package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cathedra")
@Getter
@Setter
public class Cathedra {

    @Id
    @Column(name = "id_cathedra")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "cathedra")
    private List<Teacher> teachers;

    @ManyToOne
    @JoinColumn(name = "cathedras")
    private Faculty faculty;

}
