package app.entity;

import app.entity.Interfaces.EntityInterface;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "specialties")
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Specialty implements Serializable, EntityInterface {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    private Faculty faculty;

    //TODO add to liquibase
    @OneToMany(mappedBy = "specialty")
    private List<StudentCourse> studentCourses;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}
