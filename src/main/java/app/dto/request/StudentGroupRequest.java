package app.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class StudentGroupRequest {

    private int course;
    private int number;
    private String specialtyId;
    private List<StudentSubgroupRequestModel> subgroups;
}
