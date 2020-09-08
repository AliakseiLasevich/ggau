package app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseResponse {

    private String publicId;
    private int courseNumber;
    private SpecialtyResponse specialty;
    private List<StudentGroupResponse> studentGroups;
}
