package app.dto.response;

import app.entity.Interfaces.ResponseInterface;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LearnPlanResponse implements ResponseInterface {

    private LocalDate startDate;
    private LocalDate endDate;
    private String publicId;
    private FacultyResponse faculty;
    private StudentCourseResponse studentCourse;
    private List<DisciplinePlanResponse> disciplinePlan;
}
