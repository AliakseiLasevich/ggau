package app.service.interfaces;

import app.model.dto.request.CathedraRequest;
import app.model.dto.response.CathedraResponse;
import app.model.entity.Cathedra;

import java.util.List;

public interface CathedraService {

    List<CathedraResponse> getAll();

    Cathedra createCathedra(CathedraRequest cathedraRequest, String facultyId);

    Cathedra updateCathedra(CathedraRequest cathedraRequest, String publicId);

    void deleteCathedra(String publicId);

    Cathedra findByPublicId(String cathedraId);
}
