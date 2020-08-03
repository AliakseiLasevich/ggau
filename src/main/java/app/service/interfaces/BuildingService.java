package app.service.interfaces;

import app.dto.BuildingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingService {

    BuildingDto findById(Long id);

    List<BuildingDto> findBuildings();

    void save(BuildingDto buildingDto);
}
