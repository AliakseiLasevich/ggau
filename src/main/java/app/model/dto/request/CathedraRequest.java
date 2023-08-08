package app.model.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CathedraRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String facultyId;
}
