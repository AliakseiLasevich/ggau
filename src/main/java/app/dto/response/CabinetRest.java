package app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabinetRest {

    private Long id;
    private int number;
    private String type;
    private int maxStudents;
    private Boolean active;
}