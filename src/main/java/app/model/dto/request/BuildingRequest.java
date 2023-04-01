package app.model.dto.request;

import app.model.Interfaces.RequestInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingRequest implements RequestInterface {

    private String name;
}
