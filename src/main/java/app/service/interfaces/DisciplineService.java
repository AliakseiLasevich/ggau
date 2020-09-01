package app.service.interfaces;

import app.dto.request.DisciplineRequest;
import app.dto.response.DisciplineResponse;
import app.entity.Discipline;
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
