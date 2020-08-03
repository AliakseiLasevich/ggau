package app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSubgroupRequestModel {

    private int course;
    private Long studentGroupId;
    private int number;
    private Boolean active;
    private String name;
}
