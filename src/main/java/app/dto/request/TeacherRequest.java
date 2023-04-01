package app.dto.request;


import app.entity.Interfaces.RequestInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequest implements RequestInterface {

    private String name;
    private String cathedraId;
    private String note;
}
