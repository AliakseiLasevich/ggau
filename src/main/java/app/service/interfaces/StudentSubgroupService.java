package app.service.interfaces;


import app.dto.request.StudentSubgroupRequest;
import app.dto.response.StudentSubgroupResponse;
import app.entity.StudentSubgroup;

import java.util.List;

public interface StudentSubgroupService {

    StudentSubgroupResponse findByPublicId(String publicId);

    StudentSubgroupResponse createStudentsSubgroup(StudentSubgroupRequest studentSubgroupRequest);

    StudentSubgroupResponse updateStudentsSubgroup(StudentSubgroupRequest studentSubgroupRequest, String publicId);

    void deleteStudentSubgroup(String publicId);

    List<StudentSubgroupResponse> findByGroupId(String publicId);

    List<StudentSubgroup> findAllByStudentCourse(String courseId);


}
