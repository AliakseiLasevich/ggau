package service.interfaces;

import dto.CathedraDto;
import entity.Cathedra;

import java.util.List;

public interface CathedraService {
    List<CathedraDto> findAll(int page, int limit);
    Cathedra findById(Long id);
}
