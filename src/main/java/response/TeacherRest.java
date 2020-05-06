package response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRest {

    private Long id;
    private String name;
    private CathedraRest cathedra;

}
