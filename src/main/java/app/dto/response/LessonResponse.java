package app.dto.response;

import app.entity.LessonType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
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
