package app.model.dto.response;

import app.model.Interfaces.ResponseInterface;
import app.model.entity.LessonsPerWeek;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DisciplinePlanResponse implements ResponseInterface {
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
