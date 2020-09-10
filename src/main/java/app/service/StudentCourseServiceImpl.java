package app.service;

import app.converters.StudentCourseMapper;
import app.dao.interfaces.StudentCourseRepository;
import app.dto.request.StudentCourseRequest;
import app.dto.response.StudentCourseResponse;
import app.entity.Specialty;
import app.entity.StudentCourse;
import app.exception.ErrorMessages;
import app.exception.StudentCourseException;
import app.service.interfaces.SpecialtyService;
import app.service.interfaces.StudentCourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;
    private final SpecialtyService specialtyService;

    public StudentCourseServiceImpl(StudentCourseRepository studentCourseRepository, SpecialtyService specialtyService) {
        this.studentCourseRepository = studentCourseRepository;
        this.specialtyService = specialtyService;
    }

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
                .collect(Collectors.toList());
    }

    @Override
    public StudentCourse findEntityByPublicId(String publicId) {
        StudentCourse studentCourse = studentCourseRepository.findByPublicIdAndActiveTrue(publicId);
        if (studentCourse == null) {
            throw new StudentCourseException(ErrorMessages.NO_STUDENT_COURSE_FOUND.getErrorMessage());
        }
        return studentCourse;
    }
}
