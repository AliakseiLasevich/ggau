package service.interfaces;

import dto.CabinetDto;

import java.util.List;

public interface CabinetService {
    List<CabinetDto> findCabinetsByParams(Long buildingId);

    CabinetDto findById(Long id);

    void save(CabinetDto cabinetDto);
}
