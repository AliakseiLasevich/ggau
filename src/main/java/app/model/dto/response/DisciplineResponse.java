package app.model.dto.response;

import app.model.Interfaces.ResponseInterface;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DisciplineResponse implements ResponseInterface {

    private String name;
    private String publicId;
}
