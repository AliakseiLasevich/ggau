package app.service;

import app.dao.interfaces.BuildingRepository;
import app.dto.request.BuildingRequest;
import app.dto.response.BuildingResponse;
import app.entity.Building;
import app.exception.BuildingException;
import app.exception.ErrorMessages;
import app.mappers.BuildingMapper;
import app.service.interfaces.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    @Override
    public List<BuildingResponse> findBuildings() {
        List<Building> buildings = buildingRepository.findAllByActiveTrue();
        return buildings.stream()
                .map(BuildingMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BuildingResponse findById(String publicId) {
        Building building = checkBuildingExist(publicId);
        return BuildingMapper.INSTANCE.entityToResponse(building);
    }

    @Override
    public Building findEntityByPublicId(String publicId) {
        return buildingRepository.findByPublicIdAndActiveTrue(publicId);
    }

    @Override
    public BuildingResponse createBuilding(BuildingRequest buildingRequest) {
        checkBuildingNotExist(buildingRequest);
        Building building = BuildingMapper.INSTANCE.requestToEntity(buildingRequest);
        building.setPublicId(UUID.randomUUID().toString());
        buildingRepository.save(building);
        return BuildingMapper.INSTANCE.entityToResponse(building);
    }

    private void checkBuildingNotExist(BuildingRequest buildingRequest) {
        Building b = buildingRepository.findByNameAndActiveTrue(buildingRequest.getName());
        if (b != null) {
            throw new BuildingException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
    }

    @Override
    public BuildingResponse updateBuilding(BuildingRequest buildingRequest, String publicId) {
        Building buildingToUpdate = checkBuildingExist(publicId);
        buildingToUpdate.setName(buildingRequest.getName());
        buildingRepository.save(buildingToUpdate);
        return BuildingMapper.INSTANCE.entityToResponse(buildingToUpdate);
    }


    @Override
    public void deleteBuilding(String publicId) {
        Building buildingToUpdate = checkBuildingExist(publicId);
        buildingToUpdate.setActive(false);
        buildingRepository.save(buildingToUpdate);

    }

    private Building checkBuildingExist(String publicId) {
        Building buildingToUpdate = buildingRepository.findByPublicIdAndActiveTrue(publicId);
        if (buildingToUpdate == null) {
            throw new BuildingException(ErrorMessages.NO_BUILDING_FOUND.getErrorMessage());
        }
        return buildingToUpdate;
    }


}
