package service;

import dao.interfaces.FacultyRepository;
import dto.FacultyDto;
import entity.Faculty;
import exception.ErrorMessages;
import exception.FacultyException;
import mappers.FacultyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.interfaces.FacultyService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Transactional
    public List<FacultyDto> findAll(int page, int limit) {


        if(page>0) page-=1;
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<Faculty> facultiesPage = facultyRepository.findAll(pageableRequest);
        List<Faculty> faculties = facultiesPage.getContent();

        return faculties.stream().map(FacultyMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }


    @Transactional
    public Faculty findById(Long id) {
        return facultyRepository
                .findById(id)
                .orElseThrow((() -> new FacultyException(ErrorMessages.NO_FACULTY_FOUND.getErrorMessage())));
    }

    @Transactional
    public FacultyDto createFaculty(FacultyDto facultyDto) {
        if (facultyRepository.findByName(facultyDto.getName()) != null) {
            throw new FacultyException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }

        Faculty facultyEntity = FacultyMapper.INSTANCE.dtoToEntity(facultyDto);
        Faculty storedFaculty = facultyRepository.save(facultyEntity);

        FacultyDto returnValue = FacultyMapper.INSTANCE.entityToDto(storedFaculty);
        return returnValue;
    }
}
