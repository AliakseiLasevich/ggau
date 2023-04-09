package app.service.interfaces;

import app.model.dto.request.CabinetRequest;
import app.model.dto.response.CabinetResponse;

import java.util.List;

public interface CabinetService {

    CabinetResponse findById(String publicId);

    List<CabinetResponse> findAll();

    CabinetResponse createCabinet(CabinetRequest cabinetRequest);

    CabinetResponse updateCabinet(CabinetRequest cabinetRequest, String buildingId);

    void deleteCabinet(String publicId);
}
