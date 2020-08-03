package app.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestModel {

    private Long id;
    private String name;
    private Long cathedraId;
    private boolean active;

}
