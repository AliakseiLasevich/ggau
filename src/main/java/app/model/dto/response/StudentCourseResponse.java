package app.model.dto.response;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseResponse {

    private String publicId;
    private int courseNumber;
    private SpecialtyResponse specialty;
}
