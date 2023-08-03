package app.model.entity;

import app.model.entity.interfaces.GeneratedId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "discipline_plan")
public class DisciplinePlan implements GeneratedId {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "app.common.CustomIdGenerator")
    private String id;

    @Column(name = "public_id")
    private String publicId;

    @OneToOne(cascade = CascadeType.ALL)
    private Discipline discipline;

    @Column(name = "hours_summary") //Общее количество часов
    private int hoursSummary;

    @Column(name = "summary") //Всего
    private int summary;

    @Column(name = "hours_cabinet") //Всего аудиторных
    private int hoursCabinet;

    @Column(name = "hours_lecture")//Кол-во часов Лекции
    private int hoursLecture;

    @Column(name = "hours_laboratory") //Кол-во часов Лабораторные
    private int hoursLaboratory;

    @Column(name = "hours_practical_seminary") //Кол-во часов Практис. + Семинарн.
    private int hoursPracticalSeminary;

    @Column(name = "hoursKSRLecture") //КСР Лекции
    private int hoursKSRLecture;

    @Column(name = "hoursKSRLaboratory") //КСР Лабораторные
    private int hoursKSRLaboratory;

    @Column(name = "hoursKSRPractical") //КСР Практические
    private int hoursKSRPractical;

    @Column(name = "hoursKSRSummary") //КСР Всего
    private int hoursKSRSummary;

    @Column(name = "test_count") //Количество зачётных единиц
    private int testCount;

    @Column(name = "flow") //Поток
    private String flow;

    @OneToMany(cascade = CascadeType.ALL) //Расписание по датам и кол-во практических и лекционных за конкр. дату
    @JoinTable(name = "date_lessons_per_week_mapping",
            joinColumns = {@JoinColumn(name = "discipline_plan_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_per_week_id", referencedColumnName = "id")})
    private Map<LocalDate, LessonsPerWeek> lessons;

    @Column(name = "exam") //Экзамен
    private boolean exam;

    @Column(name = "test") //Зачёт
    private boolean test;

    @Column(name = "course_project") //Курсовой проект
    private boolean courseProject;

    @Column(name = "course_work") //Курсовая работа
    private boolean courseWork;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "learn_plan_id")
    @JsonIgnore
    private LearnPlan learnPlan;

    @Column(name = "active") //Зачёт
    private boolean active;

    @PrePersist
    public void setDefaultActiveValue() {
        active = true;
    }

    @Override
    public String getPrefix() {
        return "DPLAN";
    }
}
