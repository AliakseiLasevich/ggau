package app.model.dto.response;

import app.model.Interfaces.ResponseInterface;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupResponse implements ResponseInterface {

    private String publicId;
    private int number;
    private List<StudentSubgroupResponse> studentSubgroups;
}
