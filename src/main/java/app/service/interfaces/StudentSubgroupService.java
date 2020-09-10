package app.service.interfaces;


import app.dto.request.StudentSubgroupRequest;
import app.dto.response.StudentSubgroupResponse;

public interface StudentSubgroupService {

    StudentSubgroupResponse findByPublicId(String publicId);

    StudentSubgroupResponse createStudentsSubgroup(StudentSubgroupRequest studentSubgroupRequest);

    StudentSubgroupResponse updateStudentsSubgroup(StudentSubgroupRequest studentSubgroupRequest, String publicId);
}
