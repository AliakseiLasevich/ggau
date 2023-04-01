package app.dto.request;


import app.entity.Interfaces.RequestInterface;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyRequest implements RequestInterface {

    private String publicId;
    private String name;

}
