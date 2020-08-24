package app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyResponse {

    private String publicId;
    private String name;
    private String code;
    private FacultyResponse faculty;

}
