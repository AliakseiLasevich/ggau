package app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "lessons_per_week")
@Getter
@Setter
@NoArgsConstructor
public class LessonsPerWeek {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "app.common.CustomIdGenerator")
    private String id;

    @Column(name = "lecture")
    private int lecture;

    @Column(name = "practical")
    private int practical;

    @Column(name = "laboratory")
    private int laboratory;

}
