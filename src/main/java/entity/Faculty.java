package entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "faculty")
@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Cathedra> cathedras;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Specialty> specialties;

//    @ColumnDefault(value = "'1'")
    @Column(name = "active")
    private Boolean active;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }


}
