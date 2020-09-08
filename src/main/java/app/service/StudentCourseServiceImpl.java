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

//    TODO отрефакторить. Сильно отрефакторить))
    @Override
    public StudentCourseResponse createStudentCourse(StudentCourseRequest studentCourseRequest) {
        Specialty specialty = specialtyService.findEntityByPublicId(studentCourseRequest.getSpecialtyId());
        StudentCourse studentCourse = studentCourseRepository.findByCourseNumberAndSpecialtyAndActiveTrue(studentCourseRequest.getCourseNumber(), specialty);
        checkStudentCourseExists(studentCourse);

        //Добавляем специальность
        studentCourse = new StudentCourse();
        studentCourse.setSpecialty(specialty);

        //Добавляем номер курса
        studentCourse.setCourseNumber(studentCourseRequest.getCourseNumber());

        //Добавляем уникальный айдишник для курса
        studentCourse.setPublicId(UUID.randomUUID().toString());

        //Создать и добавить студенческие группы.
        //Создать и добавить подгруппы А и Б
        StudentCourse finalStudentCourse = studentCourse;
        List<StudentGroup> studentGroups = studentCourseRequest.getStudentGroups().stream()
                .map(studentGroupRequest -> {
                    List<StudentSubgroup> studentSubgroupList = new ArrayList<>();

                    StudentGroup studentGroup = new StudentGroup();
                    studentGroup.setPublicId(UUID.randomUUID().toString());
                    studentGroup.setStudentsCount(studentGroupRequest.getStudentsInGroup());

                    if (studentGroupRequest.getStudentsInSubgroupA() != 0) {
                        StudentSubgroup studentSubgroupA = new StudentSubgroup();
                        studentSubgroupA.setStudentsCount(studentGroupRequest.getStudentsInSubgroupA());
                        studentSubgroupA.setName("a");
                        studentSubgroupA.setPublicId(UUID.randomUUID().toString());
                        studentSubgroupA.setStudentGroup(studentGroup);
                        studentSubgroupList.add(studentSubgroupA);
                    }

                    if (studentGroupRequest.getStudentsInSubgroupB() != 0) {
                        StudentSubgroup studentSubgroupB = new StudentSubgroup();
                        studentSubgroupB.setName("b");
                        studentSubgroupB.setPublicId(UUID.randomUUID().toString());;
                        studentSubgroupB.setStudentsCount(studentGroupRequest.getStudentsInSubgroupB());
                        studentSubgroupB.setStudentGroup(studentGroup);
                        studentSubgroupList.add(studentSubgroupB);
                    }

                    studentGroup.setStudentSubgroups(studentSubgroupList);

                    studentGroup.setNumber(studentCourseRequest.getStudentGroups().indexOf(studentGroupRequest));

                    studentGroup.setStudentCourse(finalStudentCourse);
                    return studentGroup;
                }).collect(Collectors.toList());

        studentCourse.setStudentGroups(studentGroups);
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
