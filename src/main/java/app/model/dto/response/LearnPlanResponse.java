package app.model.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LearnPlanResponse {

    private LocalDate startDate;
    private LocalDate endDate;
    private String publicId;
    private FacultyResponse faculty;
    private StudentCourseResponse studentCourse;
    private List<DisciplinePlanResponse> disciplinePlan;
}
