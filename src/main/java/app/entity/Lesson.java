package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lessons")
@Data
public class Lesson implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "active")
    private boolean active;

    @Enumerated(EnumType.STRING)
    private LessonType type;

    //  1/8 lesson per day
    @Column(name = "order")
    private int order;

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
