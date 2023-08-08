package app.service;

import app.dao.interfaces.CabinetRepository;
import app.exception.CabinetException;
import app.exception.ErrorMessages;
import app.exception.errors.ErrorMessage;
import app.model.dto.request.CabinetRequest;
import app.model.dto.response.CabinetResponse;
import app.model.entity.Building;
import app.model.entity.Cabinet;
import app.model.mapper.CabinetMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CabinetService {
    private final CabinetRepository cabinetRepository;
    private final BuildingService buildingService;
    private final CabinetMapper cabinetMapper;

    public CabinetResponse findByPublicId(String publicId) {
        Cabinet cabinet = findEntityByPublicId(publicId);
        return cabinetMapper.entityToResponse(cabinet);
    }

    Cabinet findEntityByPublicId(String publicId) {
        return cabinetRepository.findByPublicIdAndActiveTrue(publicId)
                .orElseThrow(() -> {
                    log.error("Cabinet not found: " + publicId);
                    return new CabinetException("Cabinet not found: " + publicId);
                });
    }

    public List<CabinetResponse> findAll() {
        return cabinetRepository.findByActiveTrueAndBuildingActiveTrue().stream()
                .map(cabinetMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CabinetResponse createCabinet(CabinetRequest cabinetRequest) {
        Building building = buildingService.getBuildingById(cabinetRequest.getBuildingId());
        Cabinet cabinet = cabinetRepository.findByNumberAndBuildingAndActiveTrue(cabinetRequest.getNumber(), building);
        if (cabinet != null) {
            throw new CabinetException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        cabinet = cabinetMapper.requestToEntity(cabinetRequest);
        cabinet.setPublicId(UUID.randomUUID().toString());
        cabinet.setBuilding(building);
        cabinetRepository.save(cabinet);
        log.info("Кабинет создан: " + cabinet.getNumber() + "/" + cabinet.getBuilding().getName());
        return cabinetMapper.entityToResponse(cabinet);
    }

    @Transactional
    public CabinetResponse updateCabinet(CabinetRequest cabinetRequest, String publicId) {
        Cabinet cabinet = findEntityByPublicId(publicId);
        if (StringUtils.equals(cabinetRequest.getBuildingId(), cabinet.getBuilding().getPublicId())) {
            throw new CabinetException("Cabinet can't be moved to other building");
        }
        cabinet.setNumber(cabinetRequest.getNumber());
        cabinet.setMaxStudents(cabinetRequest.getMaxStudents());
        cabinet.setType(cabinetRequest.getType());
        cabinetRepository.save(cabinet);
        return cabinetMapper.entityToResponse(cabinet);
    }

    @Transactional
    public void deleteCabinet(String publicId) {
        Cabinet cabinet = findEntityByPublicId(publicId);
        cabinet.setActive(false);
        cabinetRepository.save(cabinet);
    }

    public void validateCabinetOverlapping(String id, int orderNumber, LocalDate date, List<ErrorMessage> errorMessages) {
        Optional<Cabinet> cabinetOptional = cabinetRepository.getByParameters(id, orderNumber, date);
        cabinetOptional.ifPresent(cabinet -> {
            log.error("Кабинет с указанными параметрами уже занят {}", cabinet);
            errorMessages.add(new ErrorMessage("Кабинет с указанными параметрами уже занят"));
        });
    }
}
