package app.model.dto.response;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CathedraResponse {

    private String publicId;
    private String name;
    private FacultyResponse faculty;
}
