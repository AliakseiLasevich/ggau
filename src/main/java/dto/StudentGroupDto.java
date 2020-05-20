package dto;

import entity.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupDto {

    private Long id;
    private int number;
    private int course;
    private Boolean active;
    private SpecialtyDto specialty;
    private Long specialtyId;


}
