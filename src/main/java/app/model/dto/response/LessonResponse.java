package app.model.dto.response;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LessonResponse {

    private String publicId;
    private String type;
    private long orderNumber;
    @JsonRawValue
    private LocalDate date;
    private DisciplineResponse discipline;
    private TeacherResponse teacher;
    private CabinetResponse cabinet;
    private List<StudentSubgroupResponse> studentSubgroups;
}
