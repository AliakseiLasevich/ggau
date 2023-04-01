package app.service.interfaces;

import app.model.dto.request.BuildingRequest;
import app.model.dto.response.BuildingResponse;
import app.model.entity.Building;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingService {

    List<BuildingResponse> findBuildings();

    BuildingResponse findById(String publicId);

    BuildingResponse createBuilding(BuildingRequest buildingRequest);

    BuildingResponse updateBuilding(BuildingRequest buildingRequest, String publicId);

    void deleteBuilding(String publicId);

    Building findEntityByPublicId(String publicId);
}
