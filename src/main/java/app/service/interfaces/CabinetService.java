package app.service.interfaces;

import app.dto.request.CabinetsRequest;
import app.dto.response.CabinetResponse;

import java.util.List;

public interface CabinetService {

    CabinetResponse findById(String publicId);

    List<CabinetResponse> findByBuilding(String buildingId);

    List<CabinetResponse> findAll();

    CabinetResponse createCabinet(CabinetsRequest cabinetsRequest, String buildingId);

    CabinetResponse updateCabinet(CabinetsRequest cabinetsRequest, String buildingId);

    void deleteCabinet(String publicId);
}
