package app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupRest {

    private Long id;
    private int number;
    private int course;
    private Boolean active;
    private Long specialtyId;
    private String specialtyName;
//    private SpecialtyDto specialty;
}
