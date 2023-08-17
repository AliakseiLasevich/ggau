package app.model.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "learn_plan")
public class LearnPlan extends PublicEntity {

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "active")
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    private Faculty faculty;

    @OneToOne(cascade = CascadeType.ALL)
    private StudentCourse studentCourse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "learnPlan")
    private List<DisciplinePlan> disciplinePlan;

    @Override
    public String getPrefix() {
        return "LPLAN";
    }

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}
