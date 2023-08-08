package app.service;

import app.dao.interfaces.StudentCourseRepository;
import app.exception.ErrorMessages;
import app.exception.StudentCourseException;
import app.model.dto.request.StudentCourseRequest;
import app.model.dto.response.StudentCourseResponse;
import app.model.entity.Faculty;
import app.model.entity.Specialty;
import app.model.entity.StudentCourse;
import app.model.mapper.StudentCourseMapper;
import app.service.interfaces.SpecialtyService;
import app.service.interfaces.StudentCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;
    private final SpecialtyService specialtyService;
    private final FacultyService facultyService;

    @Override
    public StudentCourseResponse createStudentCourse(StudentCourseRequest studentCourseRequest) {
        Specialty specialty = specialtyService.findEntityByPublicId(studentCourseRequest.getSpecialtyId());
        StudentCourse studentCourse = studentCourseRepository.findByCourseNumberAndSpecialtyAndActiveTrue(studentCourseRequest.getCourseNumber(), specialty);
        checkStudentCourseNotExists(studentCourse);
        studentCourse = new StudentCourse();
        studentCourse.setSpecialty(specialty);
        studentCourse.setCourseNumber(studentCourseRequest.getCourseNumber());
        studentCourse.setPublicId(UUID.randomUUID().toString());
        studentCourseRepository.save(studentCourse);
        return StudentCourseMapper.INSTANCE.entityToResponse(studentCourse);
    }

    private void checkStudentCourseNotExists(StudentCourse studentCourse) {
        if (studentCourse != null) {
            throw new StudentCourseException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
    }

    @Override
    public List<StudentCourseResponse> getAllStudentCourses() {
        List<StudentCourse> studentCourses = studentCourseRepository.findAllByActiveTrue();
        return studentCourses.stream()
                .map(StudentCourseMapper.INSTANCE::entityToResponse)
                .toList();
    }

    @Override
    public StudentCourse findEntityByPublicId(String publicId) {
        StudentCourse studentCourse = studentCourseRepository.findByPublicIdAndActiveTrue(publicId);
        if (studentCourse == null) {
            throw new StudentCourseException(ErrorMessages.NO_STUDENT_COURSE_FOUND.getErrorMessage());
        }
        return studentCourse;
    }

    @Override
    public StudentCourseResponse updateStudentCourse(StudentCourseRequest studentCourseRequest, String publicId) {
        Specialty specialty = specialtyService.findEntityByPublicId(studentCourseRequest.getSpecialtyId());

        StudentCourse studentCourseToCheck = studentCourseRepository.findByCourseNumberAndSpecialtyAndActiveTrue(studentCourseRequest.getCourseNumber(), specialty);
        checkStudentCourseNotExists(studentCourseToCheck);

        StudentCourse studentCourse = findEntityByPublicId(publicId);
        studentCourse.setCourseNumber(studentCourseRequest.getCourseNumber());

        studentCourse.setSpecialty(specialty);
        studentCourseRepository.save(studentCourse);
        return StudentCourseMapper.INSTANCE.entityToResponse(studentCourse);
    }

    @Override
    public void deleteStudentCourse(String publicId) {
        StudentCourse studentCourse = findEntityByPublicId(publicId);
        studentCourse.setActive(false);
        studentCourseRepository.save(studentCourse);
    }

    @Override
    public List<StudentCourseResponse> getStudentsCoursesByFaculty(String facultyId) {
        Faculty faculty = facultyService.findEntityByPublicId(facultyId);
        List<StudentCourse> studentCourses = studentCourseRepository.findAllBySpecialty_FacultyAndActiveTrue(faculty);
        return studentCourses.stream()
                .map(StudentCourseMapper.INSTANCE::entityToResponse)
                .toList();
    }

    @Override
    public StudentCourseResponse getStudentCourseByPublicId(String publicId) {
        StudentCourse studentCourse = findEntityByPublicId(publicId);
        return StudentCourseMapper.INSTANCE.entityToResponse(studentCourse);
    }
}
