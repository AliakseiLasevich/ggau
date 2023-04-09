package app.service;

import app.dao.interfaces.CathedraRepository;
import app.exception.CathedraException;
import app.exception.ErrorMessages;
import app.model.dto.request.CathedraRequest;
import app.model.dto.response.CathedraResponse;
import app.model.entity.Cathedra;
import app.model.entity.Faculty;
import app.model.mapper.CathedraMapper;
import app.service.interfaces.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CathedraService {

    private final CathedraRepository cathedraRepository;
    private final FacultyService facultyService;
    private final CathedraMapper cathedraMapper;

    public Cathedra findByPublicId(String cathedraId) {
        return getCathedra(cathedraId);
    }

    private Cathedra getCathedra(String cathedraId) {
        return cathedraRepository.findByPublicIdAndActiveTrue(cathedraId).orElseThrow(() -> new CathedraException("Cathedra not found: " + cathedraId));
    }

    public List<CathedraResponse> getAll() {
        List<Cathedra> cathedras = cathedraRepository.findAllByActiveTrue();
        return cathedras.stream()
                .map(cathedraMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public Cathedra createCathedra(CathedraRequest cathedraRequest, String facultyId) {
        Cathedra cathedra = cathedraRepository.findByNameAndActiveTrue(cathedraRequest.getName());
        if (cathedra != null) {
            throw new CathedraException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        cathedra = cathedraMapper.requestToEntity(cathedraRequest);
        cathedra.setPublicId(UUID.randomUUID().toString());
        Faculty faculty = facultyService.findEntityByPublicId(facultyId);
        cathedra.setFaculty(faculty);
        return cathedraRepository.save(cathedra);
    }


    @Transactional
    public Cathedra updateCathedra(CathedraRequest cathedraRequest, String publicId) {
        Cathedra cathedra = getCathedra(publicId);
        cathedra.setName(cathedraRequest.getName());
        Faculty f = facultyService.findEntityByPublicId(cathedraRequest.getFacultyId());
        cathedra.setFaculty(f);
        return cathedraRepository.save(cathedra);
    }


    @Transactional
    public void deleteCathedra(String publicId) {
        Cathedra cathedra = getCathedra(publicId);
        cathedra.setActive(false);
        cathedraRepository.save(cathedra);
    }
}
