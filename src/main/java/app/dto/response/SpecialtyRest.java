package app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyRest {

    private Long id;
    private String name;
    private String code;
    private Long facultyId;
    private Boolean active;
}
