package app.dto.request;

import app.entity.Interfaces.RequestInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingRequest implements RequestInterface {

    private String name;
}
