package app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class Lesson extends PublicEntity {

    @Column(name = "active")
    private boolean active;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private LessonType type;

    //  1/8 lesson per day
    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "cabinet_id")
    private Cabinet cabinet;

    @ManyToMany
    @JoinTable(name = "lessons_student_subgroups",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "students_subgroup_id"))
    private List<StudentSubgroup> studentSubgroups;

    @Column(name = "note")
    private String note;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }

    @Override
    public String getPrefix() {
        return "LESSN";
    }
}
