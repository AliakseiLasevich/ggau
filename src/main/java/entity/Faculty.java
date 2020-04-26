package entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "faculty")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Faculty implements Serializable {

    @Id
    @Column(name = "id_faculty")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Cathedra> cathedras;

    @JsonIgnore
    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Specialty> specialties;

}
