package app.controller;

import app.model.dto.request.LessonRequest;
import app.model.dto.response.LessonResponse;
import app.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(code = HttpStatus.CREATED)
    public LessonResponse createLesson(@RequestBody LessonRequest lessonRequest) {
        return lessonService.createLesson(lessonRequest);
    }

    @GetMapping("/lessons")
    public List<LessonResponse> lessons() {
        return lessonService.findAll();
    }

    @GetMapping(value = "/student_courses/{courseId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<LessonResponse> getLessonsByStudentsCourseAndDate(@PathVariable("courseId") String courseId,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDate firstDate,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDate lastDate) {
        return lessonService.getLessonsByStudentsCourseAndDate(courseId, firstDate, lastDate);
    }

}
