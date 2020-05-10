package service;

import dao.interfaces.CathedraRepository;
import dto.CathedraDto;
import dto.FacultyDto;
import entity.Cathedra;
import exception.CathedraException;
import exception.ErrorMessages;
import mappers.CathedraMapper;
import mappers.FacultyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.interfaces.CathedraService;
import service.interfaces.FacultyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CathedraServiceImpl implements CathedraService {

    @Autowired
    CathedraRepository cathedraRepository;

    @Autowired
    FacultyService facultyService;

    @Transactional
    @Override
    public List<CathedraDto> findCathedrasByParams(Long facultyId) {
        if (facultyId != null && facultyId > 0) {
            return findCathedraByFaculty(facultyId);
        } else {
            return findAllCathedras();
        }
    }

    @Transactional
    public List<CathedraDto> findAllCathedras() {
        List<Cathedra> cathedraEntities = cathedraRepository.cathedrasWithFaculty();
        List<CathedraDto> returnvalue = cathedraEntities.stream()
                .map(cathedra -> {
                    CathedraDto cathedraDto = CathedraMapper.INSTANCE.entityToDto(cathedra);
                    FacultyDto facultyDto = FacultyMapper.INSTANCE.entityToDto(cathedra.getFaculty());
                    cathedraDto.setFacultyDto(facultyDto);
                    return cathedraDto;
                })
                .collect(Collectors.toList());
        return returnvalue;
    }

    private List<CathedraDto> findCathedraByFaculty(Long facultyId) {
        List<CathedraDto> cathedras = cathedraRepository.findByFacultyId(facultyId).stream()
                .map(cathedra -> {
                    CathedraDto cathedraDto = CathedraMapper.INSTANCE.entityToDto(cathedra);
                    FacultyDto facultyDto = FacultyMapper.INSTANCE.entityToDto(cathedra.getFaculty());
                    cathedraDto.setFacultyDto(facultyDto);
                    return cathedraDto;
                })
                .collect(Collectors.toList());
        return cathedras;
    }


    @Transactional
    @Override
    public Cathedra findById(Long id) {
        return cathedraRepository
                .findById(id)
                .orElseThrow((() -> new CathedraException(ErrorMessages.NO_CATHEDRA_FOUND.getErrorMessage())));
    }

    @Override
    public void createCathedra(CathedraDto cathedraDto) {
        Cathedra cathedra = CathedraMapper.INSTANCE.dtoToEntity(cathedraDto);
        cathedra.setFaculty(facultyService.findById(cathedraDto.getFacultyId()));
        cathedraRepository.save(cathedra);
    }
}
