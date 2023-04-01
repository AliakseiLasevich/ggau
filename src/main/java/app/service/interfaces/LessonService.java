package app.service.interfaces;

import app.model.dto.request.LessonRequest;
import app.model.dto.response.LessonResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface LessonService {

    List<LessonResponse>  findAll();

    LessonResponse createLesson(LessonRequest lessonRequest);

    List<LessonResponse> getLessonsByStudentsCourseAndDate(String courseId, LocalDate firstDate, LocalDate lastDate);
}
