package app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentGroupRequestModel {

    private int course;
    private Long specialtyId;
    private int number;
    private Boolean active;
}
