package app.service;

import app.dao.interfaces.StudentGroupRepository;
import app.dto.request.StudentGroupRequest;
import app.dto.response.StudentGroupResponse;
import app.entity.StudentGroup;
import app.exception.ErrorMessages;
import app.exception.StudentGroupException;
import app.converters.StudentGroupMapper;
import app.service.interfaces.SpecialtyService;
import app.service.interfaces.StudentGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    private final StudentGroupRepository studentGroupRepository;

    private final SpecialtyService specialtyService;

    public StudentGroupServiceImpl(StudentGroupRepository studentGroupRepository, SpecialtyService specialtyService) {
        this.studentGroupRepository = studentGroupRepository;
        this.specialtyService = specialtyService;
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
        return null;
    }

    @Override
    public StudentGroupResponse createStudentGroup(StudentGroupRequest studentGroupRequest) {
        StudentGroup studentGroup = StudentGroupMapper.INSTANCE.requestToEntity(studentGroupRequest);
        studentGroup.setPublicId(UUID.randomUUID().toString());
        return null;
    }

    @Override
    public StudentGroupResponse updateStudentGroup(StudentGroupRequest studentGroupRequest, String publicId) {
        return null;
    }
}
