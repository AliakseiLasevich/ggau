package service;

import dao.interfaces.BuildingRepository;
import entity.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.interfaces.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    @Transactional
    @Override
    public Building findById(Long id) {
        return buildingRepository.findById(id).get();
    }
}
