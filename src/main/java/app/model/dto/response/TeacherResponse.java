package app.model.dto.response;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {

    private String publicId;
    private String name;
    private CathedraResponse cathedra;
    private String note;
}
