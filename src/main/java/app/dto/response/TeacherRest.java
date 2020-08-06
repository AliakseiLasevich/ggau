package app.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRest {

    private Long id;
    private String name;
    private CathedraResponse cathedra;
    private boolean active;

}
