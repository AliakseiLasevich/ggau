package app.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class DisciplinePlanRequest {
    private String discipline;
    private int courseProject;
    private int courseWork;
    private int exam;
    private String flow;
    private int hoursCabinet;
    private int hoursKSRL;
    private int hoursKSRLR;
    private int hoursKSRP;
    private int hoursKSRVS;
    private int hoursLaboratory;
    private int hoursLecture;
    private int hoursPracticalSeminary;
    private int hoursSummary;
    private int summary;
    private int test;
    private int testCount;
    private Map<LocalDate, LessonsPerWeekRequest> lessons;


}
