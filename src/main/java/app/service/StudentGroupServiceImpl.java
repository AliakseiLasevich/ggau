package app.service;

import app.converters.StudentGroupMapper;
import app.dao.interfaces.StudentGroupRepository;
import app.dto.request.StudentGroupRequest;
import app.dto.response.StudentGroupResponse;
import app.entity.StudentCourse;
import app.entity.StudentGroup;
import app.exception.ErrorMessages;
import app.exception.StudentGroupException;
import app.service.interfaces.StudentCourseService;
import app.service.interfaces.StudentGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;
    private final StudentCourseService studentCourseService;


    public StudentGroupServiceImpl(StudentGroupRepository studentGroupRepository, StudentCourseService studentCourseService) {
        this.studentGroupRepository = studentGroupRepository;
        this.studentCourseService = studentCourseService;
    }

    @Override
    public List<StudentGroupResponse> findAll() {
        List<StudentGroup> studentGroups = studentGroupRepository.findAllByActiveTrue();
        return studentGroups.stream()
                .map(StudentGroupMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentGroupResponse findById(String publicId) {
        StudentGroup studentGroup = studentGroupRepository.findByPublicIdAndActiveTrue(publicId);
        if (studentGroup == null) {
            throw new StudentGroupException(ErrorMessages.NO_STUDENT_GROUP_FOUND.getErrorMessage());
        }
        return StudentGroupMapper.INSTANCE.entityToResponse(studentGroup);
    }

    @Override
    public StudentGroupResponse createStudentGroup(StudentGroupRequest studentGroupRequest) {
        StudentCourse studentCourse = studentCourseService.findEntityByPublicId(studentGroupRequest.getCourseId());
        StudentGroup studentGroup = studentGroupRepository.findByNumberAndStudentCourseAndActiveTrue(studentGroupRequest.getNumber(), studentCourse);
        if(studentGroup != null){
            throw new StudentGroupException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        studentGroup = StudentGroupMapper.INSTANCE.requestToEntity(studentGroupRequest);
        studentGroup.setPublicId(UUID.randomUUID().toString());
        studentGroup.setStudentCourse(studentCourse);
        studentGroupRepository.save(studentGroup);
        return StudentGroupMapper.INSTANCE.entityToResponse(studentGroup);
    }

    @Override
    public StudentGroupResponse updateStudentGroup(StudentGroupRequest studentGroupRequest, String publicId) {
        System.out.println("UPDATE");
        return null;
    }

    @Override
    public StudentGroup findEntityByPublicId(String publicId) {
        StudentGroup studentGroup = studentGroupRepository.findByPublicIdAndActiveTrue(publicId);
        if (studentGroup == null) {
            throw new StudentGroupException(ErrorMessages.NO_STUDENT_GROUP_FOUND.getErrorMessage());
        }
        return studentGroup;
    }
}
