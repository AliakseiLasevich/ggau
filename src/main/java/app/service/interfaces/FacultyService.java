package app.service.interfaces;

import app.model.dto.request.FacultyRequest;
import app.model.dto.response.FacultyResponse;
import app.model.entity.Faculty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacultyService {
    List<FacultyResponse> findAll();

    FacultyResponse findByPublicId(String publicId);

    FacultyResponse createFaculty(FacultyRequest facultyRequest);

    FacultyResponse updateFaculty(FacultyRequest facultyRequest);

    void deleteFaculty(String publicId);

    Faculty findEntityByPublicId(String facultyId);
}
