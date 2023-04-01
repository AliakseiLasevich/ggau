package app.model.dto.response;

import app.model.Interfaces.ResponseInterface;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingResponse implements ResponseInterface {

    private String publicId;
    private String name;
}
