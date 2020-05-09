package service.interfaces;

import dto.CathedraDto;
import entity.Cathedra;

import java.util.List;

public interface CathedraService {
    List<CathedraDto> findCathedras(int page, int limit, Long facultyId);
    Cathedra findById(Long id);

    void createCathedra(CathedraDto cathedraDto);
}
