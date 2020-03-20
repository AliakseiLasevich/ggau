package entity;

        import lombok.Getter;
        import lombok.Setter;

        import javax.persistence.*;
        import java.util.List;

@Entity
@Table(name = "specialty")
@Getter
@Setter
public class Specialty {

    @Id
    @Column(name = "id_specialty")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "specialty")
    private List<StudentCourse> studentCourses;

    @ManyToOne
    @JoinColumn(name = "faculty")
    private Faculty faculty;

}
