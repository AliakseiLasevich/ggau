package service;

import dao.interfaces.FacultyRepository;
import dto.FacultyDto;
import entity.Faculty;
import exception.ErrorMessages;
import exception.FacultyException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.interfaces.FacultyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Transactional
    public List<Faculty> findAll(int page, int limit) {
        List<Faculty> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<Faculty> facultiesPage = facultyRepository.findAll(pageableRequest);
        List<Faculty> faculties = facultiesPage.getContent();

        return faculties;
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
        ModelMapper modelMapper = new ModelMapper();
        Faculty facultyEntity = modelMapper.map(facultyDto, Faculty.class);

        Faculty storedFaculty = facultyRepository.save(facultyEntity);
        FacultyDto returnValue = modelMapper.map(storedFaculty, FacultyDto.class);
        return returnValue;
    }
}
