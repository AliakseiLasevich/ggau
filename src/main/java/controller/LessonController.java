package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.LessonService;

import java.util.List;

@RestController
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/lessons")
    public List<Lesson> lessons() throws JsonProcessingException {
        List<Lesson>  lessons = lessonService.findAll();
        return lessons;
    }
}
