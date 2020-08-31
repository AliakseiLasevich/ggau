package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Table(name = "discipline_plan")
public class DisciplinePlan {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "discipline")
    private String discipline;

    @Column(name = "course_project")
    private int courseProject;

    @Column(name = "course_work")
    private int courseWork;

    @Column(name = "exam")
    private int exam;

    @Column(name = "flow")
    private String flow;

    @Column(name = "hours_cabinet")
    private int hoursCabinet;

    @Column(name = "hoursKSRL")
    private int hoursKSRL;

    @Column(name = "hoursKSRLR")
    private int hoursKSRLR;

    @Column(name = "hoursKSRP")
    private int hoursKSRP;

    @Column(name = "hoursKSRVS")
    private int hoursKSRVS;

    @Column(name = "hours_laboratory")
    private int hoursLaboratory;

    @Column(name = "hours_lecture")
    private int hoursLecture;

    @Column(name = "hours_practical_seminary")
    private int hoursPracticalSeminary;

    @Column(name = "hours_summary")
    private int hoursSummary;

    @Column(name = "summary")
    private int summary;

    @Column(name = "test")
    private int test;

    @Column(name = "testCount")
    private int testCount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "date_lessons_per_week_mapping",
            joinColumns = {@JoinColumn(name = "discipline_plan_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "lesson_per_week_id", referencedColumnName = "id")})
//    @Convert(attributeName = "converted", converter = LocalDateAttributeConverter.class)
    private Map<LocalDate, LessonsPerWeek> lessons;

}
