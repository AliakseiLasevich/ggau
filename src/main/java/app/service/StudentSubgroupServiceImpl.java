package app.service;

import app.converters.StudentSubgroupMapper;
import app.dao.interfaces.StudentSubgroupRepository;
import app.dto.request.StudentSubgroupRequest;
import app.dto.response.StudentSubgroupResponse;
import app.entity.StudentGroup;
import app.entity.StudentSubgroup;
import app.exception.ErrorMessages;
import app.exception.StudentSubgroupException;
import app.service.interfaces.StudentGroupService;
import app.service.interfaces.StudentSubgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentSubgroupServiceImpl implements StudentSubgroupService {

    private final StudentSubgroupRepository studentSubgroupRepository;
    private final StudentGroupService studentGroupService;

    @Autowired
    public StudentSubgroupServiceImpl(StudentSubgroupRepository studentSubgroupRepository, StudentGroupService studentGroupService) {
        this.studentSubgroupRepository = studentSubgroupRepository;
        this.studentGroupService = studentGroupService;
    }

    @Override
    public StudentSubgroupResponse findByPublicId(String publicId) {

        return null;
    }

    @Override
    public StudentSubgroupResponse createStudentsSubgroup(StudentSubgroupRequest studentSubgroupRequest) {
        StudentGroup studentGroup = studentGroupService.findEntityByPublicId(studentSubgroupRequest.getStudentGroupId());
        StudentSubgroup studentSubgroup = studentSubgroupRepository.findByNameAndStudentGroupAndActiveTrue(studentSubgroupRequest.getName(), studentGroup);
        if (studentSubgroup != null) {
            throw new StudentSubgroupException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        studentSubgroup = StudentSubgroupMapper.INSTANCE.requestToEntity(studentSubgroupRequest);
        studentSubgroup.setStudentGroup(studentGroup);
        studentSubgroup.setPublicId(UUID.randomUUID().toString());
        studentSubgroupRepository.save(studentSubgroup);
        return StudentSubgroupMapper.INSTANCE.entityToResponse(studentSubgroup);
    }

    @Override
    public StudentSubgroupResponse updateStudentsSubgroup(StudentSubgroupRequest studentSubgroupRequest, String publicId) {
        return null;
    }
}
