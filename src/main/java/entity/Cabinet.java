package entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cabinet")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Cabinet  implements Serializable {

    @Id
    @Column(name = "id_cabinet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private int number;

    @Column(name = "max_students")
    private int maxStudents;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "building")
    private Building building;

    @ManyToMany
    @JoinTable(name = "discipline_cabinet",
            joinColumns = @JoinColumn(name = "id_cabinet"),
            inverseJoinColumns = @JoinColumn(name = "id_discipline"))
    private List<Discipline> disciplines;
}
