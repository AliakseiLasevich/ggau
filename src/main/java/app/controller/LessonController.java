package app.controller;

import app.dto.request.LessonRequest;
import app.dto.response.LessonResponse;
import app.service.interfaces.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

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
