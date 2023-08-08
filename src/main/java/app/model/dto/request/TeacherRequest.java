package app.model.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String cathedraId;

    private String note;
}
