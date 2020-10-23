package app.service;

import app.converters.LessonMapper;
import app.dao.interfaces.LessonRepository;
import app.dto.request.LessonRequest;
import app.dto.response.LessonResponse;
import app.entity.Lesson;
import app.service.interfaces.LessonService;
import app.service.interfaces.StudentCourseService;
import app.service.interfaces.StudentSubgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final StudentCourseService studentCourseService;

    private final StudentSubgroupService studentSubgroupService;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, StudentCourseService studentCourseService, StudentSubgroupService studentSubgroupService) {
        this.lessonRepository = lessonRepository;
        this.studentCourseService = studentCourseService;
        this.studentSubgroupService = studentSubgroupService;
    }

    @Override
    public List<LessonResponse> findAll() {
        List<Lesson> lessons = lessonRepository.findAllByActiveTrue();
        return lessons.stream()
                .map(LessonMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LessonResponse createLesson(LessonRequest lessonRequest) {
        return null;
    }

    @Override
    public List<LessonResponse> getLessonsByStudentsCourseAndDate(String courseId, LocalDate firstDate, LocalDate lastDate) {
        List<Lesson> lessons = lessonRepository.findAllByStudentCourseAndDateRange(courseId, firstDate, lastDate);
        return lessons.stream().map(LessonMapper.INSTANCE::entityToResponse).collect(Collectors.toList());
    }
}
