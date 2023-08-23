package app.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseResponse {

    private String publicId;
    private int courseNumber;
    private List<StudentGroupResponse> studentGroups;
    private String specialtyPublicId;
}
