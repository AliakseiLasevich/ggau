package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculty")
@Getter
@Setter
public class Faculty {

    @Id
    @Column(name = "id_faculty")
    private int id;

    @OneToMany(mappedBy = "faculty")
    private List<Cathedra> cathedras;


    @OneToMany(mappedBy = "faculty")
    private List<Specialty> specialties;

}
