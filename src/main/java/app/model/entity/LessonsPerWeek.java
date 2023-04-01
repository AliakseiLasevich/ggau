package app.model.entity;

import app.model.Interfaces.EntityInterface;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lessons_per_week")
@Getter
@Setter
@NoArgsConstructor
public class LessonsPerWeek implements EntityInterface {

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
