package app.dto;

import app.entity.Faculty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDto {

    private Long id;
    private String name;
    private String code;
    private Boolean active;
    private Long facultyId;
    private Faculty faculty;

}
