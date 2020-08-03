package app.dto;


import app.entity.Cathedra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    private Long id;
    private String name;
    private Cathedra cathedra;
    private Long cathedraId;
    private boolean active;
}
