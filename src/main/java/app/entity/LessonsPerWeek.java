package app.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "lessons_per_week")
@Data
public class LessonsPerWeek {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture")
    private int lecture;

    @Column(name = "practice")
    private int practice;

}
