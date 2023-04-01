package app.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentCourseRequest {

    private String specialtyId;
    private int courseNumber;
}
