package app.dto.response;

import app.entity.Interfaces.ResponseInterface;
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
