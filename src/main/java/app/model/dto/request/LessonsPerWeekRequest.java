package app.model.dto.request;

import app.model.Interfaces.RequestInterface;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonsPerWeekRequest implements RequestInterface {
    private int lecture;
    private int practical;
    private int laboratory;

}
