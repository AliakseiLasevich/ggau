package app.dto.request;

import app.entity.Interfaces.RequestInterface;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SpecialtyRequest implements RequestInterface {

    private String name;
    private String code;
    private String facultyId;
}
