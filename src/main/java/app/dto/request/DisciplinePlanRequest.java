package app.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class DisciplinePlanRequest {

    private String disciplinePublicId;
    private int hoursSummary;
    private int summary;
    private int hoursCabinet;
    private int hoursLecture;
    private int hoursLaboratory;
    private int hoursPracticalSeminary;
    private int hoursKSRLecture;
    private int hoursKSRLaboratory;
    private int hoursKSRPractical;
    private int hoursKSRSummary;
    private int testCount;
    private String flow;
    private Map<LocalDate, LessonsPerWeekRequest> lessons;
    private boolean exam;
    private boolean test;
    private boolean courseProject;
    private boolean courseWork;
}
