package app.service;

import app.dao.interfaces.BuildingRepository;
import app.exception.BuildingException;
import app.exception.ErrorMessages;
import app.model.dto.request.BuildingRequest;
import app.model.dto.response.BuildingResponse;
import app.model.entity.Building;
import app.model.mapper.BuildingMapper;
import app.service.interfaces.BuildingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    BuildingMapper buildingMapper;
    BuildingRepository buildingRepository;

    @Override
    public List<BuildingResponse> getAll() {
        List<Building> buildings = buildingRepository.findAllByActiveTrue();
        return buildings.stream()
                .map(building -> Optional.ofNullable(building)
                        .map(buildingMapper::entityToResponse))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public BuildingResponse getById(String publicId) {
        Building building = findByPublicId(publicId);
        return buildingMapper.entityToResponse(building);
    }

    @Override
    public Building findEntityByPublicId(String publicId) {
        return buildingRepository.findByPublicIdAndActiveTrue(publicId);
    }

    @Override
    public BuildingResponse createBuilding(BuildingRequest buildingRequest) {
        validateRequest(buildingRequest);
        validateBuildingNotExist(buildingRequest);
        Building building = saveBuilding(buildingRequest);
        return buildingMapper.entityToResponse(building);
    }

    private Building saveBuilding(BuildingRequest buildingRequest) {
        Building building = buildingMapper.requestToEntity(buildingRequest);
        building.setPublicId(UUID.randomUUID().toString());
        buildingRepository.save(building);
        return building;
    }

    private void validateRequest(BuildingRequest buildingRequest) {
        if (buildingRequest == null) {
            throw new BuildingException("Building can't be null");
        }
    }

    private void validateBuildingNotExist(BuildingRequest buildingRequest) {
        Building b = buildingRepository.findByNameAndActiveTrue(buildingRequest.getName());
        if (b != null) {
            throw new BuildingException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
    }

    @Override
    public BuildingResponse updateBuilding(BuildingRequest buildingRequest, String publicId) {
        Building buildingToUpdate = findByPublicId(publicId);
        buildingToUpdate.setName(buildingRequest.getName());
        buildingRepository.save(buildingToUpdate);
        return buildingMapper.entityToResponse(buildingToUpdate);
    }

    @Override
    public void deleteBuilding(String publicId) {
        Building buildingToUpdate = findByPublicId(publicId);
        buildingToUpdate.setActive(false);
        buildingRepository.save(buildingToUpdate);

    }

    private Building findByPublicId(String publicId) {
        Building buildingToUpdate = buildingRepository.findByPublicIdAndActiveTrue(publicId);
        if (buildingToUpdate == null) {
            throw new BuildingException(ErrorMessages.NO_BUILDING_FOUND.getErrorMessage());
        }
        return buildingToUpdate;
    }
}
