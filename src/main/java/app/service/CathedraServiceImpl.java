package app.service;

import app.dao.interfaces.CathedraRepository;
import app.dto.request.CathedraRequest;
import app.dto.response.CathedraResponse;
import app.entity.Cathedra;
import app.entity.Faculty;
import app.exception.CathedraException;
import app.exception.ErrorMessages;
import app.mappers.CathedraMapper;
import app.service.interfaces.CathedraService;
import app.service.interfaces.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CathedraServiceImpl implements CathedraService {

    private CathedraRepository cathedraRepository;

    private FacultyService facultyService;

    @Autowired
    @Lazy
    public CathedraServiceImpl(CathedraRepository cathedraRepository, FacultyService facultyService) {
        this.cathedraRepository = cathedraRepository;
        this.facultyService = facultyService;
    }

    @Override
    public List<CathedraResponse> findAll() {
        List<Cathedra> cathedras = cathedraRepository.findAllByActiveTrue();
        return cathedras.stream()
                .map(CathedraMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void createCathedra(CathedraRequest cathedraRequest, String facultyId) {
        Cathedra cathedra = cathedraRepository.findByNameAndActiveTrue(cathedraRequest.getName());
        checkCathedraForNull(cathedra);
        cathedra = CathedraMapper.INSTANCE.requestToEntity(cathedraRequest);
        cathedra.setPublicId(UUID.randomUUID().toString());
        Faculty faculty = facultyService.findEntityByPublicId(facultyId);
        cathedra.setFaculty(faculty);
        cathedraRepository.save(cathedra);
    }

    @Override
    public void updateCathedra(CathedraRequest cathedraRequest, String publicId) {
        Cathedra cathedra = cathedraRepository.findByPublicIdAndActiveTrue(publicId);
        checkCathedraForNull(cathedra);
        cathedra.setName(cathedraRequest.getName());
        Faculty f = facultyService.findEntityByPublicId(cathedraRequest.getFacultyId());
        cathedra.setFaculty(f);
        cathedraRepository.save(cathedra);
    }


    @Override
    public void deleteCathedra(String publicId) {
        Cathedra cathedra = cathedraRepository.findByPublicIdAndActiveTrue(publicId);
        checkCathedraForNull(cathedra);
        cathedra.setActive(false);
        cathedraRepository.save(cathedra);
    }

    @Override
    public void deactivateCathedrasByFaculty(Faculty faculty) {
        List<Cathedra> cathedrasToDeactivate = cathedraRepository.findAllByFaculty(faculty);
        cathedrasToDeactivate.stream()
                .peek(cathedra -> cathedra.setActive(false))
                .forEach(cathedra -> cathedraRepository.save(cathedra));
    }


    private void checkCathedraForNull(Cathedra cathedra) {
        if (cathedra == null) {
            throw new CathedraException(ErrorMessages.NO_CATHEDRA_FOUND.getErrorMessage());
        }
    }

    @Override
    public Cathedra findByPublicId(String cathedraId) {
        return cathedraRepository.findByPublicIdAndActiveTrue(cathedraId);
    }
}
