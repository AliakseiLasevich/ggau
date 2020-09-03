package app.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LearnPlanRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private String facultyId;
    private String specialtyId;
    private int courseNumber;
    private List<DisciplinePlanRequest> disciplinePlan;
}
