package app.model.dto.response;

import app.model.Interfaces.ResponseInterface;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CathedraResponse implements ResponseInterface {

    private String publicId;
    private String name;
    private FacultyResponse faculty;
}
