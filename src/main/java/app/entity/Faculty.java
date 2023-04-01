package app.entity;

import app.entity.Interfaces.EntityInterface;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "faculties")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Faculty implements Serializable, EntityInterface {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Cathedra> cathedras;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Specialty> specialties;

    @Column(name = "active")
    private boolean active;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }

}
