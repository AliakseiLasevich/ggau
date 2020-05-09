package response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CathedraRest {

    private Long id;
    private String name;
    private Boolean active;
    private FacultyRest faculty;


}
