package app.service.interfaces;

import app.dto.request.CathedraRequest;
import app.dto.response.CathedraResponse;
import app.entity.Faculty;

import java.util.List;

public interface CathedraService {

    List<CathedraResponse> findAll();

    void createCathedra(CathedraRequest cathedraRequest, String facultyId);

    void updateCathedra(CathedraRequest cathedraRequest, String publicId);

    void deactivateCathedrasByFaculty(Faculty faculty);

    void deleteCathedra(String publicId);
}
