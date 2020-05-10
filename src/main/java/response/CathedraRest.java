package response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CathedraRest {

    private Long id;
    private String name;
    private boolean active;
    private FacultyRest faculty;
}
