package app.service;

import app.dao.interfaces.CabinetRepository;
import app.dto.request.CabinetsRequest;
import app.dto.response.CabinetResponse;
import app.entity.Building;
import app.entity.Cabinet;
import app.exception.CabinetException;
import app.exception.ErrorMessages;
import app.mappers.CabinetMapper;
import app.service.interfaces.BuildingService;
import app.service.interfaces.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CabinetServiceImpl implements CabinetService {

    private final CabinetRepository cabinetRepository;

    private final BuildingService buildingService;

    @Autowired
    public CabinetServiceImpl(CabinetRepository cabinetRepository, BuildingService buildingService) {
        this.cabinetRepository = cabinetRepository;
        this.buildingService = buildingService;
    }

    @Override
    public CabinetResponse findById(String publicId) {
        Cabinet cabinet = cabinetRepository.findByPublicIdAndActiveTrue(publicId);
        checkCabinetExists(cabinet);
        return CabinetMapper.INSTANCE.entityToResponse(cabinet);
    }

    private void checkCabinetExists(Cabinet cabinet) {
        if (cabinet == null) {
            throw new CabinetException(ErrorMessages.NO_CABINET_FOUND.getErrorMessage());
        }
    }

    @Override
    public List<CabinetResponse> findAll() {
        return cabinetRepository.findByActiveTrueAndBuildingActiveTrue().stream()
                .map(CabinetMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CabinetResponse> findByBuilding(String buildingId) {
        Building building = buildingService.findEntityByPublicId(buildingId);
        return cabinetRepository.findAllByBuildingAndActiveTrue(building).stream()
                .map(CabinetMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CabinetResponse createCabinet(CabinetsRequest cabinetsRequest) {
        Building building = buildingService.findEntityByPublicId(cabinetsRequest.getBuildingId());
        Cabinet cabinet = cabinetRepository.findByNumberAndBuildingAndActiveTrue(cabinetsRequest.getNumber(), building);
        if (cabinet != null) {
            throw new CabinetException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        cabinet = CabinetMapper.INSTANCE.requestToEntity(cabinetsRequest);
        cabinet.setPublicId(UUID.randomUUID().toString());
        cabinet.setBuilding(building);
        cabinetRepository.save(cabinet);
        return CabinetMapper.INSTANCE.entityToResponse(cabinet);
    }

    @Override
    public CabinetResponse updateCabinet(CabinetsRequest cabinetsRequest, String publicId) {
        Cabinet cabinet = cabinetRepository.findByPublicIdAndActiveTrue(publicId);
        checkCabinetExists(cabinet);
        Building building = buildingService.findEntityByPublicId(cabinetsRequest.getBuildingId());
        cabinet.setNumber(cabinetsRequest.getNumber());
        cabinet.setBuilding(building);
        cabinet.setMaxStudents(cabinetsRequest.getMaxStudents());
        cabinet.setType(cabinetsRequest.getType());
        cabinetRepository.save(cabinet);
        return CabinetMapper.INSTANCE.entityToResponse(cabinet);
    }

    @Override
    public void deleteCabinet(String publicId) {
        Cabinet cabinet = cabinetRepository.findByPublicIdAndActiveTrue(publicId);
        checkCabinetExists(cabinet);
        cabinet.setActive(false);
        cabinetRepository.save(cabinet);
    }
}
