package app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialtyRequestModel {

    private String name;
    private String code;
    private Boolean active;
    private Long facultyId;
}
