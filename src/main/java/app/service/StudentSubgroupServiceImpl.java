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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        StudentSubgroup studentSubgroup = getStudentSubgroup(publicId);
        return StudentSubgroupMapper.INSTANCE.entityToResponse(studentSubgroup);
    }

    private StudentSubgroup getStudentSubgroup(String publicId) {
        StudentSubgroup studentSubgroup = studentSubgroupRepository.findByPublicIdAndActiveTrue(publicId);
        if (studentSubgroup == null) {
            throw new StudentSubgroupException(ErrorMessages.NO_STUDENT_SUBGROUP_FOUND.getErrorMessage());
        }
        return studentSubgroup;
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
        StudentSubgroup studentSubgroup = getStudentSubgroup(publicId);
        studentSubgroup.setName(studentSubgroupRequest.getName());
        studentSubgroup.setStudentsCount(studentSubgroupRequest.getStudentsCount());
        StudentGroup studentGroup = studentGroupService.findEntityByPublicId(studentSubgroupRequest.getStudentGroupId());
        studentSubgroup.setStudentGroup(studentGroup);
        studentSubgroupRepository.save(studentSubgroup);
        return StudentSubgroupMapper.INSTANCE.entityToResponse(studentSubgroup);
    }

    @Override
    public void deleteStudentSubgroup(String publicId) {
        StudentSubgroup studentSubgroup = getStudentSubgroup(publicId);
        studentSubgroup.setActive(false);
        studentSubgroupRepository.save(studentSubgroup);
    }

    @Override
    public List<StudentSubgroupResponse> findByGroupId(String publicId) {
        StudentGroup studentGroup = studentGroupService.findEntityByPublicId(publicId);
        List<StudentSubgroup> studentSubgroups = studentSubgroupRepository.findAllByStudentGroupAndActiveTrue(studentGroup);
        return studentSubgroups.stream()
                .map(StudentSubgroupMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

}
