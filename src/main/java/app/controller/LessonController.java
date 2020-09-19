package app.controller;

import app.dto.response.StudentSubgroupResponse;
import app.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import app.service.LessonService;

import java.util.List;

@RestController
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/lessons")
    public List<Lesson> lessons() {
        return lessonService.findAll();
    }

    @GetMapping(value = "/specialties/{specialtyId}/courses/{courseId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<StudentSubgroupResponse> getLessonsBySpecialtyAndCourse(@PathVariable("specialtyId") String specialtyId, @PathVariable("courseId") String courseId) {
        return null;
    }


}
