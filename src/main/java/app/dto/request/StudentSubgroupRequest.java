package app.dto.request;

import app.entity.Interfaces.RequestInterface;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSubgroupRequest implements RequestInterface {

    private String name;
    private int studentsCount;
    private String studentGroupId;
}
