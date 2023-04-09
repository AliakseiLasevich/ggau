package app.model.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CathedraRequest {

    @NotNull
    private String name;

    @NotNull
    private String facultyId;
}
