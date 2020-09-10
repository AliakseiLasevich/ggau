package app.service.interfaces;

import app.dto.request.StudentGroupRequest;
import app.dto.response.StudentGroupResponse;
import app.entity.StudentGroup;

import java.util.List;

public interface StudentGroupService {

    List<StudentGroupResponse> findAll();

    StudentGroupResponse findById(String publicId);

    StudentGroupResponse createStudentGroup(StudentGroupRequest studentGroupRequest);

    StudentGroupResponse updateStudentGroup(StudentGroupRequest studentGroupRequest, String publicId);

    StudentGroup findEntityByPublicId(String studentGroupId);
}
