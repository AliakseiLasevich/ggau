package app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CathedraResponse {

    private String publicId;
    private String name;
    private FacultyResponse faculty;
}
