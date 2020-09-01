package app.dto.response;

import app.dto.request.LessonsPerWeekRequest;
import app.entity.Discipline;
import app.entity.LessonsPerWeek;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class LearnPlanResponse {

    private String publicId;
    private Discipline discipline;
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
    private Map<LocalDate, LessonsPerWeek> lessons;
}
