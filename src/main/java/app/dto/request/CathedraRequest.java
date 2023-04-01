package app.dto.request;


import app.entity.Interfaces.RequestInterface;
import lombok.*;

@Getter
@Setter
public class CathedraRequest implements RequestInterface {

    private String name;
    private String facultyId;
}
