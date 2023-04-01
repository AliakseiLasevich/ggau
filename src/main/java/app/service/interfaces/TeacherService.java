package app.service.interfaces;

import app.model.dto.request.TeacherRequest;
import app.model.dto.response.TeacherResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    List<TeacherResponse> findAll();

    TeacherResponse findById(String publicId);

    TeacherResponse createTeacher(TeacherRequest teacherRequest);

    TeacherResponse updateTeacher(TeacherRequest teacherRequest, String publicId);

    void deleteTeacher(String publicId);
}
