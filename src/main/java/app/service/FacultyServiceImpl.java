package app.service;

import app.dao.interfaces.FacultyRepository;
import app.exception.ErrorMessages;
import app.exception.FacultyException;
import app.model.dto.request.FacultyRequest;
import app.model.dto.response.FacultyResponse;
import app.model.entity.Faculty;
import app.model.mapper.FacultyMapper;
import app.service.interfaces.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    //    private final CathedraService cathedraService;
    private final FacultyMapper facultyMapper;

    @Transactional
    public List<FacultyResponse> findAll() {
        List<Faculty> faculties = facultyRepository.findAllByActiveTrue();
        return faculties.stream()
                .map(facultyMapper::entityToResponse)
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
        Faculty facultyEntity = facultyMapper.requestToEntity(facultyRequest);
        String uuid = UUID.randomUUID().toString();
        facultyEntity.setPublicId(uuid);
        Faculty storedFaculty = facultyRepository.save(facultyEntity);
        return facultyMapper.entityToResponse(storedFaculty);
    }

    @Transactional
    @Override
    public FacultyResponse updateFaculty(FacultyRequest facultyRequest) {
        Faculty faculty = getFaculty(facultyRequest.getPublicId());

        faculty.setName(facultyRequest.getName());
        facultyRepository.save(faculty);

        return facultyMapper.entityToResponse(faculty);
    }

    @Override
    public FacultyResponse findByPublicId(String publicId) {
        return facultyMapper.entityToResponse(getFaculty(publicId));
    }

    @Override
    public Faculty findEntityByPublicId(String facultyId) {
        return getFaculty(facultyId);
    }

    private Faculty getFaculty(String facultyId) {
        return facultyRepository.findByPublicIdAndActiveTrue(facultyId).orElseThrow(() -> new FacultyException("Faculty not found: " + facultyId));
    }

    @Transactional
    @Override
    public void deleteFaculty(String publicId) {
        Faculty faculty = getFaculty(publicId);
        faculty.setActive(false);
        facultyRepository.save(faculty);
        //TODO check if required
//        cathedraService.deactivateCathedrasByFaculty(faculty);
    }
}
