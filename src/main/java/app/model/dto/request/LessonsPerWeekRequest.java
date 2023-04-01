package app.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonsPerWeekRequest {
    private int lecture;
    private int practical;
    private int laboratory;

}
