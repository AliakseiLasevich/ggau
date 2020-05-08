package request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CathedraRequestModel {

    private Long id;
    private String name;

    private Long facultyId;
    private Boolean active;

}
