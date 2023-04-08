package app.service;

import app.dao.interfaces.BuildingRepository;
import app.exception.BuildingException;
import app.exception.ErrorMessages;
import app.model.dto.request.BuildingRequest;
import app.model.dto.response.BuildingResponse;
import app.model.entity.Building;
import app.model.mapper.BuildingMapper;
import app.service.interfaces.BuildingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuildingServiceImpl implements BuildingService {

    private final BuildingMapper buildingMapper;
    private final BuildingRepository buildingRepository;


    @Override
    public List<BuildingResponse> getAll() {
        var buildings = buildingRepository.findAllByActiveTrue();
        return buildings.stream()
                .map(building -> Optional.ofNullable(building)
                        .map(buildingMapper::entityToResponse))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public BuildingResponse getById(String publicId) {
        var building = getBuildingById(publicId);
        return buildingMapper.entityToResponse(building);
    }

    @Override
    public BuildingResponse createBuilding(BuildingRequest buildingRequest) {
        validateBuildingByName(buildingRequest);
        var building = buildingRepository.save(buildingMapper.requestToEntity(buildingRequest));
        return buildingMapper.entityToResponse(building);
    }

    private void validateBuildingByName(BuildingRequest buildingRequest) {
        Building b = buildingRepository.findByNameAndActiveTrue(buildingRequest.getName());
        if (b != null) {
            log.error(String.format(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage(), b.getName()));
            throw new BuildingException(String.format(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage(), b.getName()));
        }
    }

    @Override
    public BuildingResponse updateBuilding(BuildingRequest buildingRequest, String publicId) {
        Building buildingToUpdate = getBuildingById(publicId);
        buildingToUpdate.setName(buildingRequest.getName());
        buildingRepository.save(buildingToUpdate);
        return buildingMapper.entityToResponse(buildingToUpdate);
    }

    @Override
    public void deleteBuilding(String publicId) {
        Building buildingToUpdate = getBuildingById(publicId);
        buildingToUpdate.setActive(false);
        buildingRepository.save(buildingToUpdate);
        log.info("Building with public id " + publicId + "was deactivated");
    }

    public Building getBuildingById(String publicId) {
        return buildingRepository.findByPublicIdAndActiveTrue(publicId)
                .orElseThrow(() -> {
                    log.error("Building not found " + publicId);
                    return new IllegalArgumentException("Building not found " + publicId);
                });
    }
}
