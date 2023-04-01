package app.model.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequest {

    private String name;
    private String cathedraId;
    private String note;
}
