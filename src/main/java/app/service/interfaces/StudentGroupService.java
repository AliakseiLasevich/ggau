package app.service.interfaces;

import app.model.dto.request.StudentGroupRequest;
import app.model.dto.response.StudentGroupResponse;
import app.model.entity.StudentGroup;

import java.util.List;

public interface StudentGroupService {

    List<StudentGroupResponse> findAll();

    StudentGroupResponse findById(String publicId);

    StudentGroupResponse createStudentGroup(StudentGroupRequest studentGroupRequest);

    StudentGroupResponse updateStudentGroup(StudentGroupRequest studentGroupRequest, String publicId);

    StudentGroup findEntityByPublicId(String studentGroupId);

    void deleteStudentGroup(String publicId);

    List<StudentGroupResponse> findAllByCourseId(String publicId);
}
