package app.service.interfaces;

import app.dto.request.SpecialtyRequest;
import app.dto.response.SpecialtyResponse;
import app.entity.Specialty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecialtyService {


    SpecialtyResponse findById(String publicId);

    List<SpecialtyResponse> findSpecialities();

    Specialty findEntityByPublicId(String publicId);

    SpecialtyResponse createSpecialty(SpecialtyRequest specialtyRequest);

    SpecialtyResponse updateSpecialty(SpecialtyRequest specialtyRequest, String publicId);

    void deleteSpecialty(String publicId);

    List<SpecialtyResponse> findSpecialitiesByFacultyId(String facultyId);
}
