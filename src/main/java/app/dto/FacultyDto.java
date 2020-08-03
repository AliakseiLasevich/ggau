package app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDto {

    private Long id;
    private String name;
    private boolean active;
    private Long facultyId;

}
