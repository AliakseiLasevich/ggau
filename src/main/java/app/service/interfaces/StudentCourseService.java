package app.service.interfaces;

import app.model.dto.request.StudentCourseRequest;
import app.model.dto.response.StudentCourseResponse;
import app.model.entity.StudentCourse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentCourseService {

    StudentCourseResponse createStudentCourse(StudentCourseRequest studentCourseRequest);

    List<StudentCourseResponse> getAllStudentCourses();

    StudentCourse findEntityByPublicId(String publicId);

    StudentCourseResponse updateStudentCourse(StudentCourseRequest studentCourseRequest, String publicId);

    void deleteStudentCourse(String publicId);

    List<StudentCourseResponse> getStudentsCoursesByFaculty(String facultyId);

    StudentCourseResponse getStudentCourseByPublicId(String publicId);
}
