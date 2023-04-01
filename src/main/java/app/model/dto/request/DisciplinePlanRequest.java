package app.model.dto.request;

import app.model.Interfaces.RequestInterface;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DisciplinePlanRequest implements RequestInterface {

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
