package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

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

    @Column(name = "practical")
    private int practical;

    @Column(name = "laboratory")
    private int laboratory;

}
