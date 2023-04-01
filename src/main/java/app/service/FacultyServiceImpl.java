package app.service;

import app.converters.FacultyMapper;
import app.dao.interfaces.FacultyRepository;
import app.dto.request.FacultyRequest;
import app.dto.response.FacultyResponse;
import app.entity.Faculty;
import app.exception.ErrorMessages;
import app.exception.FacultyException;
import app.service.interfaces.CathedraService;
import app.service.interfaces.FacultyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    private final CathedraService cathedraService;

    public FacultyServiceImpl(FacultyRepository facultyRepository, CathedraService cathedraService) {
        this.facultyRepository = facultyRepository;
        this.cathedraService = cathedraService;
    }

    @Transactional
    public List<FacultyResponse> findAll() {
        List<Faculty> faculties = facultyRepository.findAllByActiveTrue();
        return faculties.stream()
                .map(FacultyMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public Faculty findById(Long id) {
        return facultyRepository
                .findById(id)
                .orElseThrow((() -> new FacultyException(ErrorMessages.NO_FACULTY_FOUND.getErrorMessage())));
    }

    @Transactional
    public FacultyResponse createFaculty(FacultyRequest facultyRequest) {
        if (facultyRepository.findByNameAndActiveTrue(facultyRequest.getName()) != null) {
            throw new FacultyException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        Faculty facultyEntity = FacultyMapper.INSTANCE.requestToEntity(facultyRequest);
        String uuid = UUID.randomUUID().toString();
        facultyEntity.setPublicId(uuid);
        Faculty storedFaculty = facultyRepository.save(facultyEntity);
        return FacultyMapper.INSTANCE.entityToResponse(storedFaculty);
    }

    @Transactional
    @Override
    public FacultyResponse updateFaculty(FacultyRequest facultyRequest) {
        Faculty faculty = facultyRepository.findByPublicIdAndActiveTrue(facultyRequest.getPublicId());
        if (faculty != null) {
            faculty.setName(facultyRequest.getName());
            facultyRepository.save(faculty);
        } else throw new FacultyException(ErrorMessages.NO_FACULTY_FOUND.getErrorMessage());
        return FacultyMapper.INSTANCE.entityToResponse(faculty);
    }

    @Override
    public FacultyResponse findByPublicId(String publicId) {
        return FacultyMapper.INSTANCE.entityToResponse(facultyRepository.findByPublicIdAndActiveTrue(publicId));
    }

    @Override
    public Faculty findEntityByPublicId(String facultyId) {
        return facultyRepository.findByPublicIdAndActiveTrue(facultyId);
    }

    @Transactional
    @Override
    public void deleteFaculty(String publicId) {
        Faculty faculty = facultyRepository.findByPublicIdAndActiveTrue(publicId);
        if (faculty != null) {
            faculty.setActive(false);
            facultyRepository.save(faculty);
        } else throw new FacultyException("No such faculty");
        cathedraService.deactivateCathedrasByFaculty(faculty);
    }
}
