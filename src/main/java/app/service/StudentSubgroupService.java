package app.service;

import app.dao.interfaces.StudentSubgroupRepository;
import app.exception.ErrorMessages;
import app.exception.StudentSubgroupException;
import app.model.dto.request.StudentSubgroupRequest;
import app.model.dto.response.StudentSubgroupResponse;
import app.model.entity.StudentGroup;
import app.model.entity.StudentSubgroup;
import app.model.mapper.StudentSubgroupMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentSubgroupService {

    private final StudentSubgroupRepository studentSubgroupRepository;
    private final StudentGroupService studentGroupService;
    private final StudentSubgroupMapper studentSubgroupMapper;

    List<StudentSubgroup> findEntitiesByPublicIds(List<String> publicIds) {
        return studentSubgroupRepository.findByPublicIdsAndActiveTrue(publicIds);
    }

    public StudentSubgroupResponse findByPublicId(String publicId) {
        StudentSubgroup studentSubgroup = getStudentSubgroup(publicId);
        return studentSubgroupMapper.entityToResponse(studentSubgroup);
    }

    private StudentSubgroup getStudentSubgroup(String publicId) {
        StudentSubgroup studentSubgroup = studentSubgroupRepository.findByPublicIdAndActiveTrue(publicId);
        if (studentSubgroup == null) {
            throw new StudentSubgroupException(ErrorMessages.NO_STUDENT_SUBGROUP_FOUND.getErrorMessage());
        }
        return studentSubgroup;
    }

    public StudentSubgroupResponse createStudentsSubgroup(StudentSubgroupRequest studentSubgroupRequest) {
        StudentGroup studentGroup = studentGroupService.findEntityByPublicId(studentSubgroupRequest.getStudentGroupId());
        StudentSubgroup studentSubgroup = studentSubgroupRepository.findByNameAndStudentGroupAndActiveTrue(studentSubgroupRequest.getName(), studentGroup);
        if (studentSubgroup != null) {
            throw new StudentSubgroupException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        studentSubgroup = studentSubgroupMapper.requestToEntity(studentSubgroupRequest);
        studentSubgroup.setStudentGroup(studentGroup);
        studentSubgroup.setPublicId(UUID.randomUUID().toString());
        studentSubgroupRepository.save(studentSubgroup);
        return studentSubgroupMapper.entityToResponse(studentSubgroup);
    }

    public StudentSubgroupResponse updateStudentsSubgroup(StudentSubgroupRequest studentSubgroupRequest, String publicId) {
        StudentSubgroup studentSubgroup = getStudentSubgroup(publicId);
        studentSubgroup.setName(studentSubgroupRequest.getName());
        studentSubgroup.setStudentsCount(studentSubgroupRequest.getStudentsCount());
        StudentGroup studentGroup = studentGroupService.findEntityByPublicId(studentSubgroupRequest.getStudentGroupId());
        studentSubgroup.setStudentGroup(studentGroup);
        studentSubgroupRepository.save(studentSubgroup);
        return studentSubgroupMapper.entityToResponse(studentSubgroup);
    }

    public void deleteStudentSubgroup(String publicId) {
        StudentSubgroup studentSubgroup = getStudentSubgroup(publicId);
        studentSubgroup.setActive(false);
        studentSubgroupRepository.save(studentSubgroup);
    }

    public List<StudentSubgroupResponse> findByGroupId(String publicId) {
        StudentGroup studentGroup = studentGroupService.findEntityByPublicId(publicId);
        List<StudentSubgroup> studentSubgroups = studentSubgroupRepository.findAllByStudentGroupAndActiveTrue(studentGroup);
        return studentSubgroups.stream()
                .map(studentSubgroupMapper::entityToResponse)
                .toList();
    }
}
