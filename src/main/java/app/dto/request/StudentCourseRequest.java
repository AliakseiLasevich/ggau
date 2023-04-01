package app.dto.request;

import app.entity.Interfaces.RequestInterface;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentCourseRequest implements RequestInterface {

    private String specialtyId;
    private int courseNumber;
}
