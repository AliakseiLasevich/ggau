package service;

import dao.interfaces.CathedraRepository;
import dto.CathedraDto;
import dto.FacultyDto;
import entity.Cathedra;
import entity.Faculty;
import exception.CathedraException;
import exception.ErrorMessages;
import mappers.CathedraMapper;
import mappers.FacultyMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<CathedraDto> findCathedras(int page, int limit, Long facultyId) {

        if (facultyId != null && facultyId > 0) {
            return findCathedraByFaculty(facultyId);
        } else {
            return findAllCathedras(page, limit);
        }
    }

    private List<CathedraDto> findAllCathedras(int page, int limit) {
        if (page > 0) page -= 1;
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Cathedra> cathedrasPage = cathedraRepository.findAll(pageableRequest);

        List<Cathedra> cathedraEntities =
                cathedrasPage.getContent().stream()
                        .peek(cathedra -> cathedra.setFaculty((Faculty) Hibernate.unproxy(cathedra.getFaculty())))
                        .collect(Collectors.toList());


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
        List<Cathedra> cathedras = cathedraRepository.findByFacultyId(facultyId);
        return cathedras.stream()
                .map(CathedraMapper.INSTANCE::entityToDto).collect(Collectors.toList());
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
