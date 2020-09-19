package app.service.interfaces;

import app.dto.request.LessonRequest;
import app.dto.response.LessonResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface LessonService {

    List<LessonResponse>  findAll();

    LessonResponse createLesson(LessonRequest lessonRequest);

    List<LessonResponse> getLessonsByStudentsCourseAndDate(String courseId, LocalDate date);
}
