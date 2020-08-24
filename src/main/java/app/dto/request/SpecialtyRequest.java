package app.dto.request;

import lombok.Data;

@Data
public class SpecialtyRequest {

    private String name;
    private String code;
    private String facultyId;
}
