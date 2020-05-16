package request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentGroupRequestModel {

    private int course;
    private int specialtyId;
    private int number;
    private Boolean active;
}
