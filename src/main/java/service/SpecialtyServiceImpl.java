package service;

import dao.interfaces.SpecialtyRepository;
import dto.SpecialtyDto;
import entity.Faculty;
import entity.Specialty;
import mappers.SpecialtyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.interfaces.FacultyService;
import service.interfaces.SpecialtyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    SpecialtyRepository specialtyRepository;

    @Autowired
    FacultyService facultyService;

    @Override
    public SpecialtyDto findById(Long id) {
        return SpecialtyMapper.INSTANCE.entityToDto(specialtyRepository.findById(id).get());
    }

    @Override
    public List<SpecialtyDto> findSpecialities() {


        return specialtyRepository
                .findAll()
                .stream()
                .map(SpecialtyMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(SpecialtyDto specialtyDto) {
        Specialty specialty = SpecialtyMapper.INSTANCE.dtoToEntity(specialtyDto);
        Faculty faculty = facultyService.findById(specialtyDto.getFacultyId());
        specialty.setFaculty(faculty);
        specialtyRepository.save(specialty);
    }
}
