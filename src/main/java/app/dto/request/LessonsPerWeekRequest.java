package app.dto.request;

import lombok.Data;

@Data
public class LessonsPerWeekRequest {
    private int lecture;
    private int practical;
    private int laboratory;

}
