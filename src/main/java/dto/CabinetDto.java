package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabinetDto {

    private Long id;
    private String type;
    private int number;
    private int maxStudents;
    private Boolean active;
    private Long buildingId;
}
