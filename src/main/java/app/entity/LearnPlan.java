package app.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "course_number")
    private int courseNumber;

    @Column(name = "active")
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    private Faculty faculty;

    @OneToOne(cascade = CascadeType.ALL)
    private Specialty specialty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "learnPlan")
    private List<DisciplinePlan> disciplinePlan;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}
