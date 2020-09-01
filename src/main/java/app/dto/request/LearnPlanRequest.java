package app.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class LearnPlanRequest {

    private String facultyId;
    private String specialtyId;
    private int courseNumber;
    private List<DisciplinePlanRequest> disciplinePlan;
}
