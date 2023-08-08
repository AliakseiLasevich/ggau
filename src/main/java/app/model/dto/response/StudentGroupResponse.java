package app.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupResponse {

    private String publicId;
    private int number;
    private List<StudentSubgroupResponse> studentSubgroups;
}
