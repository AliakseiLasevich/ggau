package app.service;

import app.dao.interfaces.StudentGroupRepository;
import app.exception.ErrorMessages;
import app.exception.StudentGroupException;
import app.model.dto.request.StudentGroupRequest;
import app.model.dto.response.StudentGroupResponse;
import app.model.entity.StudentCourse;
import app.model.entity.StudentGroup;
import app.model.mapper.StudentGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;
    private final StudentCourseService studentCourseService;
    private final StudentGroupMapper studentGroupMapper;


    public List<StudentGroupResponse> findAll() {
        List<StudentGroup> studentGroups = studentGroupRepository.findAllByActiveTrue();
        return studentGroups.stream()
                .map(studentGroupMapper::entityToResponse)
                .toList();
    }

    public StudentGroupResponse findById(String publicId) {
        StudentGroup studentGroup = studentGroupRepository.findByPublicIdAndActiveTrue(publicId);
        checkStudentGroupExists(studentGroup == null, ErrorMessages.NO_STUDENT_GROUP_FOUND);
        return studentGroupMapper.entityToResponse(studentGroup);
    }

    public StudentGroupResponse createStudentGroup(StudentGroupRequest studentGroupRequest) {
        StudentCourse studentCourse = studentCourseService.findEntityByPublicId(studentGroupRequest.getCourseId());
        checkStudentGroupForNoDuplicates(studentGroupRequest, studentCourse);
        StudentGroup studentGroup;
        studentGroup = studentGroupMapper.requestToEntity(studentGroupRequest);
        studentGroup.setPublicId(UUID.randomUUID().toString());
        studentGroup.setStudentCourse(studentCourse);
        studentGroupRepository.save(studentGroup);
        return studentGroupMapper.entityToResponse(studentGroup);
    }

    public StudentGroupResponse updateStudentGroup(StudentGroupRequest studentGroupRequest, String publicId) {
        StudentGroup studentGroup = studentGroupRepository.findByPublicIdAndActiveTrue(publicId);
        checkStudentGroupExists(studentGroup == null, ErrorMessages.NO_STUDENT_GROUP_FOUND);
        StudentCourse studentCourse = studentCourseService.findEntityByPublicId(studentGroupRequest.getCourseId());
        checkStudentGroupForNoDuplicates(studentGroupRequest, studentCourse);
        studentGroup.setNumber(studentGroupRequest.getNumber());
        studentGroupRepository.save(studentGroup);
        return studentGroupMapper.entityToResponse(studentGroup);
    }

    private void checkStudentGroupExists(boolean b, ErrorMessages noStudentGroupFound) {
        if (b) {
            throw new StudentGroupException(noStudentGroupFound.getErrorMessage());
        }
    }

    private void checkStudentGroupForNoDuplicates(StudentGroupRequest studentGroupRequest, StudentCourse studentCourse) {
        StudentGroup studentGroup = studentGroupRepository.findByNumberAndStudentCourseAndActiveTrue(studentGroupRequest.getNumber(), studentCourse);
        checkStudentGroupExists(studentGroup != null, ErrorMessages.RECORD_ALREADY_EXISTS);
    }


    public StudentGroup findEntityByPublicId(String publicId) {
        StudentGroup studentGroup = studentGroupRepository.findByPublicIdAndActiveTrue(publicId);
        checkStudentGroupExists(studentGroup == null, ErrorMessages.NO_STUDENT_GROUP_FOUND);
        return studentGroup;
    }

    public void deleteStudentGroup(String publicId) {
        StudentGroup studentGroup = findEntityByPublicId(publicId);
        studentGroup.setActive(false);
        studentGroup.getStudentSubgroups().forEach(studentSubgroup -> studentSubgroup.setActive(false));
        studentGroupRepository.save(studentGroup);
    }

    public List<StudentGroupResponse> findAllByCourseId(String courseId) {
        StudentCourse course = studentCourseService.findEntityByPublicId(courseId);
        List<StudentGroup> studentGroups = studentGroupRepository.findAllByStudentCourseAndActiveTrue(course);
        return studentGroups.stream()
                .map(studentGroupMapper::entityToResponse)
                .toList();
    }
}
