package app.service;

import app.converters.StudentCourseMapper;
import app.dao.interfaces.StudentCourseRepository;
import app.dto.request.StudentCourseRequest;
import app.dto.response.StudentCourseResponse;
import app.entity.Specialty;
import app.entity.StudentCourse;
import app.entity.StudentGroup;
import app.entity.StudentSubgroup;
import app.exception.ErrorMessages;
import app.exception.StudentCourseException;
import app.service.interfaces.SpecialtyService;
import app.service.interfaces.StudentCourseService;
import app.service.interfaces.StudentGroupService;
import app.service.interfaces.StudentSubgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private StudentGroupService studentGroupService;

    @Autowired
    private StudentSubgroupService studentSubgroupService;

    @Override
    public StudentCourseResponse createStudentCourse(StudentCourseRequest studentCourseRequest) {
        Specialty specialty = specialtyService.findEntityByPublicId(studentCourseRequest.getSpecialtyId());
        StudentCourse studentCourse = studentCourseRepository.findByCourseNumberAndSpecialtyAndActiveTrue(studentCourseRequest.getCourseNumber(), specialty);
        checkStudentCourseExists(studentCourse);
        studentCourse = new StudentCourse();
        studentCourse.setSpecialty(specialty);
        studentCourse.setCourseNumber(studentCourseRequest.getCourseNumber());
        studentCourse.setPublicId(UUID.randomUUID().toString());
        studentCourseRepository.save(studentCourse);
        return StudentCourseMapper.INSTANCE.entityToResponse(studentCourse);
    }

    private void checkStudentCourseExists(StudentCourse studentCourse) {
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
}
