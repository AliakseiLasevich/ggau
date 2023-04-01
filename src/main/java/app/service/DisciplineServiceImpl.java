package app.service;

import app.converters.DisciplineMapper;
import app.dao.interfaces.DisciplineRepository;
import app.dto.request.DisciplineRequest;
import app.dto.response.DisciplineResponse;
import app.entity.Discipline;
import app.exception.DisciplineException;
import app.exception.ErrorMessages;
import app.service.interfaces.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    private DisciplineMapper disciplineMapper;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Override
    public Discipline findEntityByPublicId(String publicId) {
        Discipline discipline = disciplineRepository.findByPublicIdAndActiveTrue(publicId);
        checkDisciplineExists(discipline);
        return discipline;
    }

    @Override
    public List<DisciplineResponse> findAll() {
        return disciplineRepository.findAllByActiveTrue().stream()
                .map(discipline -> Optional.ofNullable(discipline)
                        .map(disciplineMapper::entityToResponse))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public DisciplineResponse findByPublicId(String publicId) {
        Discipline discipline = disciplineRepository.findByPublicIdAndActiveTrue(publicId);
        checkDisciplineExists(discipline);
        return disciplineMapper.entityToResponse(discipline);
    }

    private void checkDisciplineExists(Discipline discipline) {
        if (discipline == null) {
            throw new DisciplineException(ErrorMessages.NO_DISCIPLINE_FOUND.getErrorMessage());
        }
    }

    @Override
    public DisciplineResponse createDiscipline(DisciplineRequest disciplineRequest) {
        Discipline discipline = disciplineMapper.requestToEntity(disciplineRequest);
        discipline.setPublicId(UUID.randomUUID().toString());
        disciplineRepository.save(discipline);
        return disciplineMapper.entityToResponse(discipline);
    }

    @Override
    public DisciplineResponse updateDiscipline(DisciplineRequest disciplineRequest, String publicId) {
        Discipline discipline = disciplineRepository.findByPublicIdAndActiveTrue(publicId);
        checkDisciplineExists(discipline);
        discipline.setName(disciplineRequest.getName());
        disciplineRepository.save(discipline);
        return disciplineMapper.entityToResponse(discipline);
    }

    @Override
    public void deleteDiscipline(String publicId) {
        Discipline discipline = disciplineRepository.findByPublicIdAndActiveTrue(publicId);
        checkDisciplineExists(discipline);
        discipline.setActive(false);
        disciplineRepository.save(discipline);
    }
}
