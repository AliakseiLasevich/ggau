package app.dto.request;

import lombok.Data;

@Data
public class StudentGroupRequest {

    private int studentsInGroup;
    private int studentsInSubgroupA;
    private int studentsInSubgroupB;
}
