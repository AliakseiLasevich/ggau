package service;

import dao.interfaces.LessonRepository;
import entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;


    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }
}
