package app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CabinetsRequestModel {

    private Long buildingId;
    private int number;
    private int maxStudents;
    private String type;
    private Boolean active;

}
