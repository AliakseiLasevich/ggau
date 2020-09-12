package app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseResponse {

    private String publicId;
    private int courseNumber;
    private SpecialtyResponse specialty;
}
