package app.controller;

import app.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
