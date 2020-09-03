package app.dto.response;

import app.entity.LessonsPerWeek;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class DisciplinePlanResponse {
    private String publicId;
    private DisciplineResponse discipline;
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
    private Map<LocalDate, LessonsPerWeek> lessons;
    private boolean exam;
    private boolean test;
    private boolean courseProject;
    private boolean courseWork;
}
