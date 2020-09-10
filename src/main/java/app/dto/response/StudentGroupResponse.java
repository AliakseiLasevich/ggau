package app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupResponse {

    private String publicId;
    private int number;
    private List<StudentSubgroupResponse> studentSubgroups;
}
