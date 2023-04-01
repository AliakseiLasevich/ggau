package app.dto.request;

import app.entity.Interfaces.RequestInterface;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentGroupRequest implements RequestInterface {

    private int number;
    private String courseId;

}
