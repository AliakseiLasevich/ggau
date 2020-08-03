package app.service.interfaces;

import app.dto.TeacherDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    List<TeacherDto> findAll(int page, int limit);

    void postTeacher(TeacherDto teacherDto);

    TeacherDto findById(Long id);
}
