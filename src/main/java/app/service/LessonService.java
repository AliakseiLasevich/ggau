package app.service;

import app.dao.interfaces.LessonRepository;
import app.model.dto.request.LessonRequest;
import app.model.dto.response.LessonResponse;
import app.model.entity.Lesson;
import app.model.mapper.LessonMapper;
import app.service.interfaces.StudentCourseService;
import app.service.interfaces.StudentSubgroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    private final StudentCourseService studentCourseService;

    private final StudentSubgroupService studentSubgroupService;
    private final LessonMapper lessonMapper;


    public List<LessonResponse> findAll() {
        List<Lesson> lessons = lessonRepository.findAllByActiveTrue();
        return lessons.stream()
                .map(LessonMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    public LessonResponse createLesson(LessonRequest lessonRequest) {
//        Lesson l = Lesson.builder()
//                .cabinet(lessonRequest.)
//                .build();
//        return lessonMapper.entityToResponse(le);
        return null;
    }


    public List<LessonResponse> getLessonsByStudentsCourseAndDate(String courseId, LocalDate firstDate, LocalDate lastDate) {
        List<Lesson> lessons = lessonRepository.findAllByStudentCourseAndDateRange(courseId, firstDate, lastDate);
        return lessons.stream().map(LessonMapper.INSTANCE::entityToResponse).collect(Collectors.toList());
    }
}
