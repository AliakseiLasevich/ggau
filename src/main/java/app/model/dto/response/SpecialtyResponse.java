package app.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyResponse {

    private String publicId;
    private String name;
    private String code;
    private FacultyResponse faculty;

}
