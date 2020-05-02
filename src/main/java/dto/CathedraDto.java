package dto;

import entity.Faculty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CathedraDto {

    private Long id;
    private String name;
    private Faculty faculty;


}

