package app.model.dto.request;

import app.model.Interfaces.RequestInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CabinetsRequest implements RequestInterface {

    private String number;
    private int maxStudents;
    private String type;
    private String buildingId;
}
