package app.service.interfaces;

import app.dto.CathedraDto;
import app.entity.Cathedra;

import java.util.List;

public interface CathedraService {


    List<CathedraDto> findCathedrasByParams(Long facultyId);

    Cathedra findById(Long id);

    void createCathedra(CathedraDto cathedraDto);

    void updateCathedra(CathedraDto cathedraDto);
}
