package app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CabinetsRequest {

    private String number;
    private int maxStudents;
    private String type;
    private String buildingId;
}
