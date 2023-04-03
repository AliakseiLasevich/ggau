package app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "custom-id")
    private String id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "active")
    private boolean active;

    @Enumerated(EnumType.STRING)
    private LessonType type;

    //  1/8 lesson per day
    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

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

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }
}