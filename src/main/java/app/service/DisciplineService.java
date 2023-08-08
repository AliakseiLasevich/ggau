package app.service;

import app.dao.interfaces.DisciplineRepository;
import app.exception.DisciplineException;
import app.model.dto.request.DisciplineRequest;
import app.model.dto.response.DisciplineResponse;
import app.model.entity.Discipline;
import app.model.mapper.DisciplineMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DisciplineService {

    private final DisciplineMapper disciplineMapper;
    private final DisciplineRepository disciplineRepository;


    Discipline findEntityByPublicId(String publicId) {
        return disciplineRepository.findByPublicIdAndActiveTrue(publicId)
                .orElseThrow(() -> {
                    log.error("Discipline not found: {}", publicId);
                    throw new DisciplineException("Discipline not found: " + publicId);
                });
    }

    public List<DisciplineResponse> findAll() {
        return disciplineRepository.findAllByActiveTrue().stream()
                .map(disciplineMapper::entityToResponse)
                .toList();
    }

    public DisciplineResponse findByPublicId(String publicId) {
        Discipline discipline = findEntityByPublicId(publicId);
        return disciplineMapper.entityToResponse(discipline);
    }

    @Transactional
    public DisciplineResponse createDiscipline(DisciplineRequest disciplineRequest) {
        Discipline discipline = disciplineMapper.requestToEntity(disciplineRequest);
        discipline.setPublicId(UUID.randomUUID().toString());
        Discipline saved = disciplineRepository.save(discipline);
        log.info("New discipline created: {}", discipline.getName());
        return disciplineMapper.entityToResponse(saved);
    }

    @Transactional
    public DisciplineResponse updateDiscipline(DisciplineRequest disciplineRequest, String publicId) {
        Discipline discipline = findEntityByPublicId(publicId);
        discipline.setName(disciplineRequest.getName());
        Discipline updated = disciplineRepository.save(discipline);
        log.info("discipline was updated: {}", discipline.getName());
        return disciplineMapper.entityToResponse(updated);
    }

    @Transactional
    public void deleteDiscipline(String publicId) {
        Discipline discipline = findEntityByPublicId(publicId);
        discipline.setActive(false);
        disciplineRepository.save(discipline);
        log.info("Discipline was removed: {}", discipline.getName());
    }

}
