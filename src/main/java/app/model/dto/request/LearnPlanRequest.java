package app.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LearnPlanRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private String facultyId;
    private String studentsCourseId;
    private List<DisciplinePlanRequest> disciplinePlan;
}
