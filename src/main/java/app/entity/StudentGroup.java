package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "students_groups")
@Data
public class StudentGroup implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "course")
    private int course;

    @Column(name = "number")
    private int number;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @OneToMany(mappedBy = "studentGroup")
    private List<StudentSubgroup> studentSubgroups;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}
