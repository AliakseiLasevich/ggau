package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cabinet")
@Getter
@Setter
public class Cabinet {

    @Id
    @Column(name = "id_cabinet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "building")
    private Building building;

    @ManyToMany
    @JoinTable(name = "discipline_cabinet",
            joinColumns = @JoinColumn(name = "id_cabinet"),
            inverseJoinColumns = @JoinColumn(name = "id_discipline"))
    private List<Discipline> disciplines;
}
