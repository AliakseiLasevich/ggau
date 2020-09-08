package app.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {

    private String publicId;
    private String name;
    private CathedraResponse cathedra;
    private String note;
}
