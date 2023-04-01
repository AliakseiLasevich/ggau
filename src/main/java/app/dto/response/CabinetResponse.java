package app.dto.response;

import app.entity.Interfaces.RequestInterface;
import app.entity.Interfaces.ResponseInterface;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CabinetResponse implements ResponseInterface {

    private String publicId;
    private String number;
    private String type;
    private int maxStudents;
    private String buildingId;
    private String buildingName;

}
