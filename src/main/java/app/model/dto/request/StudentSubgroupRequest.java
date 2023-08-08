package app.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSubgroupRequest {

    @NotEmpty
    private String name;

    @Positive
    private int studentsCount;

    @NotEmpty
    private String studentGroupId;
}
