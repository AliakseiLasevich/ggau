package app.model.dto.request;


import app.model.Interfaces.RequestInterface;
import lombok.*;

@Getter
@Setter
public class CathedraRequest implements RequestInterface {

    private String name;
    private String facultyId;
}
