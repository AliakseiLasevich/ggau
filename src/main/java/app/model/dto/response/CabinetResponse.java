package app.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
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
