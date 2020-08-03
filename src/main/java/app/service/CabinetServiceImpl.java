package app.service;

import app.dao.interfaces.BuildingRepository;
import app.dao.interfaces.CabinetRepository;
import app.dto.CabinetDto;
import app.entity.Cabinet;
import app.mappers.CabinetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.service.interfaces.BuildingService;
import app.service.interfaces.CabinetService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CabinetServiceImpl implements CabinetService {

    @Autowired
    CabinetRepository cabinetRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    BuildingService buildingService;

    @Transactional
    @Override
    public List<CabinetDto> findCabinetsByParams(Long buildingId) {
        if (buildingId == null) {
            return findAllCabinets();
        } else return findCabinetsByBuildingId(buildingId);
    }

    private List<CabinetDto> findCabinetsByBuildingId(Long buildingId) {
        return cabinetRepository.findByBuildingId(buildingId).stream().map(CabinetMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    public List<CabinetDto> findAllCabinets() {
        return cabinetRepository
                .findAll()
                .stream()
                .map(CabinetMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CabinetDto findById(Long id) {
        return CabinetMapper.INSTANCE.entityToDto(cabinetRepository.findById(id).get());
    }

    @Transactional
    @Override
    public void save(CabinetDto cabinetDto) {
        Cabinet cabinet = CabinetMapper.INSTANCE.dtoToEntity(cabinetDto);
        cabinet.setBuilding(buildingRepository.findById(cabinetDto.getBuildingId()).get());
        cabinetRepository.save(cabinet);
    }
}
