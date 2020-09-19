package app.controller;

import app.dto.request.LessonRequest;
import app.dto.response.LessonResponse;
import app.service.interfaces.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public List<LessonResponse> getLessonsByStudentsCourse(@PathVariable("courseId") String courseId) {
        return null;
    }

}
