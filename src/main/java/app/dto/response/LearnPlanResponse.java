package app.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LearnPlanResponse {

    private LocalDate startDate;
    private LocalDate endDate;
    private String publicId;
    private FacultyResponse faculty;
    private StudentCourseResponse studentCourse;
    private List<DisciplinePlanResponse> disciplinePlan;
}
