package service;

import dao.interfaces.BuildingRepository;
import dto.BuildingDto;
import mappers.BuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.interfaces.BuildingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    BuildingRepository buildingRepository;



    @Transactional
    @Override
    public BuildingDto findById(Long id) {
        return BuildingMapper.INSTANCE.entityToDto(buildingRepository.findById(id).get());
    }

    @Transactional
    @Override
    public List<BuildingDto> findBuildings() {
        return buildingRepository
                .findAll().stream()
                .map(BuildingMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(BuildingDto buildingDto) {
        buildingRepository.save(BuildingMapper.INSTANCE.dtoToEntity(buildingDto));
    }
}
