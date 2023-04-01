package app.service;

import app.model.mapper.SpecialtyMapper;
import app.dao.interfaces.SpecialtyRepository;
import app.model.dto.request.SpecialtyRequest;
import app.model.dto.response.SpecialtyResponse;
import app.model.entity.Faculty;
import app.model.entity.Specialty;
import app.exception.ErrorMessages;
import app.exception.SpecialtyException;
import app.service.interfaces.FacultyService;
import app.service.interfaces.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    private final FacultyService facultyService;

    @Autowired
    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository, FacultyService facultyService) {
        this.specialtyRepository = specialtyRepository;
        this.facultyService = facultyService;
    }

    @Override
    public SpecialtyResponse findById(String publicId) {
        Specialty specialty = specialtyRepository.findByPublicIdAndActiveTrue(publicId);
        checkSpecialtyExists(specialty);
        return SpecialtyMapper.INSTANCE.entityToResponse(specialty);
    }

    @Override
    public List<SpecialtyResponse> findSpecialities() {
        return specialtyRepository
                .findAllByActiveTrue()
                .stream()
                .map(SpecialtyMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialtyResponse createSpecialty(SpecialtyRequest specialtyRequest) {
        Specialty specialty = specialtyRepository.findByCodeAndActiveTrue(specialtyRequest.getCode());
        if (specialty != null) {
            throw new SpecialtyException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        specialty = SpecialtyMapper.INSTANCE.requestToEntity(specialtyRequest);
        Faculty faculty = facultyService.findEntityByPublicId(specialtyRequest.getFacultyId());
        specialty.setFaculty(faculty);
        specialty.setPublicId(UUID.randomUUID().toString());
        specialtyRepository.save(specialty);
        return SpecialtyMapper.INSTANCE.entityToResponse(specialty);
    }

    @Override
    public SpecialtyResponse updateSpecialty(SpecialtyRequest specialtyRequest, String publicId) {
        Specialty specialty = specialtyRepository.findByPublicIdAndActiveTrue(publicId);
        checkSpecialtyExists(specialty);
        Faculty faculty = facultyService.findEntityByPublicId(specialtyRequest.getFacultyId());
        specialty.setFaculty(faculty);
        specialty.setCode(specialtyRequest.getCode());
        specialty.setName(specialtyRequest.getName());
        specialtyRepository.save(specialty);
        return SpecialtyMapper.INSTANCE.entityToResponse(specialty);
    }

    @Override
    public void deleteSpecialty(String publicId) {
        Specialty specialty = specialtyRepository.findByPublicIdAndActiveTrue(publicId);
        checkSpecialtyExists(specialty);
        specialty.setActive(false);
        specialtyRepository.save(specialty);
    }

    private void checkSpecialtyExists(Specialty specialty) {
        if (specialty == null) {
            throw new SpecialtyException(ErrorMessages.NO_SPECIALTY_FOUND.getErrorMessage());
        }
    }

    @Override
    public Specialty findEntityByPublicId(String publicId) {
        Specialty specialty = specialtyRepository.findByPublicIdAndActiveTrue(publicId);
        checkSpecialtyExists(specialty);
        return specialty;
    }

    @Override
    public List<SpecialtyResponse> findSpecialitiesByFacultyId(String facultyId) {
        Faculty faculty = facultyService.findEntityByPublicId(facultyId);
        List<Specialty> specialties = specialtyRepository.findByFacultyAndActiveTrue(faculty);
        return specialties.stream()
                .map(SpecialtyMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }
}

