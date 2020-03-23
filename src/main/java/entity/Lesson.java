package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "lesson")
@Getter
@Setter
public class Lesson {

    @Id
    @Column(name = "id_lesson")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "discipline")
    private Discipline discipline;

    @OneToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "building")
    private Building building;

    @OneToOne
    @JoinColumn(name = "cabinet")
    private Cabinet cabinet;

    @ManyToOne
    @JoinColumn(name = "day")
    private Day day;

    //  1/8 lesson per day
    @Column(name = "time")
    private int time;

    @Column(name = "type")
    private String type;

    @ManyToMany
    @JoinTable(name = "lesson_studentsubgroup",
            joinColumns = @JoinColumn(name = "id_lesson"),
            inverseJoinColumns = @JoinColumn(name = "id_subgroup"))
    private List<StudentSubgroup> studentSubgroups;


}
