package app.service.interfaces;

import app.dto.request.StudentCourseRequest;
import app.dto.response.StudentCourseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentCourseService {

    StudentCourseResponse createStudentCourse(StudentCourseRequest studentCourseRequest);

    List<StudentCourseResponse> getAllStudentCourses();
}
