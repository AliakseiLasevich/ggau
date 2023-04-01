package app.dto.response;

import app.entity.Interfaces.RequestInterface;
import app.entity.Interfaces.ResponseInterface;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacultyResponse implements ResponseInterface {

    private String publicId;
    private String name;
}
