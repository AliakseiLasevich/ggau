package app.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SpecialtyRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String code;

    @NotEmpty
    private String facultyId;
}
