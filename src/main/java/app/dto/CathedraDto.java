package app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CathedraDto {

    private Long id;
    private String name;
    private boolean active;
    private Long facultyId;
    private FacultyDto facultyDto;
}

