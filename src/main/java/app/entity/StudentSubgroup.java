package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student_subgroups")
@Data
public class StudentSubgroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "name")
    private String name;

    @Column(name = "students_count")
    private int studentsCount;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_group_id")
    private StudentGroup studentGroup;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}
