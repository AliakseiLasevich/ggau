package service.interfaces;

import entity.Building;
import org.springframework.stereotype.Service;

@Service
public interface BuildingService {

    Building findById(Long id);
}
