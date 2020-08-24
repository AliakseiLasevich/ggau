package app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabinetResponse {

    private String publicId;
    private String number;
    private String type;
    private int maxStudents;
    private String buildingId;
    private String buildingName;

}
