package app.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CathedraResponse {

    private String publicId;
    private String name;
    private FacultyResponse faculty;
}
