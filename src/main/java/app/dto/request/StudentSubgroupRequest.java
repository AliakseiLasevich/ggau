package app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSubgroupRequest {

    private String name;
    private String studentsCount;
    private String studentGroupId;
}
