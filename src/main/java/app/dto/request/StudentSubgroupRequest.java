package app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSubgroupRequest {

    private String name;
    private int studentsCount;
    private String studentGroupId;
}
