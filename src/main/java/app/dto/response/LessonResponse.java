package app.dto.response;

import app.entity.Interfaces.ResponseInterface;
import app.entity.LessonType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LessonResponse implements ResponseInterface {

    private String publicId;
    private LessonType type;
    private int order;
    private LocalDateTime dateTime;
    private DisciplineResponse discipline;
    private TeacherResponse teacher;
    private CabinetResponse cabinet;
    private List<StudentSubgroupResponse> studentSubgroups;
}
