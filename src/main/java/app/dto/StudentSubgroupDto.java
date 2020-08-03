package app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubgroupDto {

    private Long id;
    private String name;
    private Boolean active;
    private Long studentGroupId;
}
