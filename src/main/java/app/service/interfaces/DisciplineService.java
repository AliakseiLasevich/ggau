package app.service.interfaces;

import app.model.dto.request.DisciplineRequest;
import app.model.dto.response.DisciplineResponse;
import app.model.entity.Discipline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DisciplineService {

    Discipline findEntityByPublicId(String disciplinePublicId);

    List<DisciplineResponse> findAll();

    DisciplineResponse findByPublicId(String publicId);

    DisciplineResponse createDiscipline(DisciplineRequest disciplineRequest);

    DisciplineResponse updateDiscipline(DisciplineRequest disciplineRequest, String publicId);

    void deleteDiscipline(String publicId);
}
