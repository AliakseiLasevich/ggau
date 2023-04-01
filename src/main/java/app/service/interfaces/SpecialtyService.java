package app.service.interfaces;

import app.model.dto.request.SpecialtyRequest;
import app.model.dto.response.SpecialtyResponse;
import app.model.entity.Specialty;
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
