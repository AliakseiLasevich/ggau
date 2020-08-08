package app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabinetResponse {

    private String publicId;
    private int number;
    private String type;
    private int maxStudents;
}
