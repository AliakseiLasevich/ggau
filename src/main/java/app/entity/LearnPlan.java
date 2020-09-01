package app.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "learn_plan")
public class LearnPlan {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "course_number")
    private int courseNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Faculty faculty;

    @OneToOne(cascade = CascadeType.ALL)
    private Specialty specialty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "learnPlan")
    private List<DisciplinePlan> disciplinePlan;
}