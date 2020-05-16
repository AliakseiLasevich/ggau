package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubgroupRest {

    private Long id;
    private String name;
    private int number;
    private int course;
    private Boolean active;
//    private SpecialtyDto specialty;
}
