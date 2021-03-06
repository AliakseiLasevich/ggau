package app.service.interfaces;

import app.dto.request.StudentCourseRequest;
import app.dto.response.SpecialtyResponse;
import app.dto.response.StudentCourseResponse;
import app.entity.StudentCourse;
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
