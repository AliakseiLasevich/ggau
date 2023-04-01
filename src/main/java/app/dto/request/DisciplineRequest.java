package app.dto.request;

import app.entity.Interfaces.RequestInterface;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DisciplineRequest implements RequestInterface {

    private String name;
}
