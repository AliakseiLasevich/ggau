package app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CabinetsRequest {

    private int number;
    private int maxStudents;
    private String type;
}
