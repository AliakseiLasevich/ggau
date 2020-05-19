package entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "specialty")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Specialty implements Serializable {

    @Id
    @Column(name = "id_specialty")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

//    @OneToMany(mappedBy = "specialty")
////    private List<StudentCourse> studentCourses;

    @OneToMany(mappedBy = "specialty")
    private List<StudentGroup> studentGroups;

    @ManyToOne
    @JoinColumn(name = "faculty")
    private Faculty faculty;


    @Column(name = "active")
    private boolean active;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}
