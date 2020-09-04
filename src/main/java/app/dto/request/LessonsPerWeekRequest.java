package app.dto.request;

import lombok.Data;

@Data
public class LessonsPerWeekRequest {
    private int lecture;
    private int practice;
    private int laboratory;

}
