package entity;

        import lombok.Getter;
        import lombok.Setter;

        import javax.persistence.*;
        import java.util.List;

@Entity
@Table(name = "faculty")
@Getter
@Setter
public class Specialty {

    @Id
    @Column(name = "id_specialty")
    private int id;

    @OneToMany(mappedBy = "specialty")
    private List<StudentCourse> studentCourses;

    @ManyToOne
    @JoinColumn(name = "faculty")
    private Faculty faculty;

}
