package app.model.dto.response;

import app.model.entity.LessonType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LessonResponse {

    private String publicId;
    private LessonType type;
    private int order;
    private LocalDateTime dateTime;
    private DisciplineResponse discipline;
    private TeacherResponse teacher;
    private CabinetResponse cabinet;
    private List<StudentSubgroupResponse> studentSubgroups;
}
