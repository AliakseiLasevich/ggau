package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CathedraRest {

    private Long id;
    private String name;
    private FacultyRest faculty;
}
