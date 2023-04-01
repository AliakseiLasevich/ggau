package app.model.dto.response;

import app.model.Interfaces.ResponseInterface;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyResponse implements ResponseInterface {

    private String publicId;
    private String name;
    private String code;
    private FacultyResponse faculty;

}
