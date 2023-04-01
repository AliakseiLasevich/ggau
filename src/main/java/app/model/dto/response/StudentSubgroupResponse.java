package app.model.dto.response;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubgroupResponse {

    private String publicId;
    private String name;
    private int studentsCount;

}
