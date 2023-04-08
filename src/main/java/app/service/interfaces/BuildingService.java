package app.service.interfaces;

import app.model.dto.request.BuildingRequest;
import app.model.dto.response.BuildingResponse;
import app.model.entity.Building;

import java.util.List;

public interface BuildingService {

    List<BuildingResponse> getAll();

    BuildingResponse getById(String publicId);

    BuildingResponse createBuilding(BuildingRequest buildingRequest);

    BuildingResponse updateBuilding(BuildingRequest buildingRequest, String publicId);

    void deleteBuilding(String publicId);

    Building getBuildingById(String publicId);
}
