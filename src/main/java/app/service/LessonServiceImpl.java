package app.service;

import app.converters.LessonMapper;
import app.dao.interfaces.LessonRepository;
import app.dto.request.LessonRequest;
import app.dto.response.LessonResponse;
import app.entity.Lesson;
import app.service.interfaces.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

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
}
