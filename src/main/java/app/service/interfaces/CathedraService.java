package app.service.interfaces;

import app.model.dto.request.CathedraRequest;
import app.model.dto.response.CathedraResponse;
import app.model.entity.Cathedra;
import app.model.entity.Faculty;

import java.util.List;

public interface CathedraService {

    List<CathedraResponse> findAll();

    void createCathedra(CathedraRequest cathedraRequest, String facultyId);

    void updateCathedra(CathedraRequest cathedraRequest, String publicId);

    void deactivateCathedrasByFaculty(Faculty faculty);

    void deleteCathedra(String publicId);

    Cathedra findByPublicId(String cathedraId);
}
