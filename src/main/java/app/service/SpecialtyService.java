package app.service;

import app.dao.interfaces.SpecialtyRepository;
import app.exception.ErrorMessages;
import app.exception.SpecialtyException;
import app.model.dto.request.SpecialtyRequest;
import app.model.dto.response.SpecialtyResponse;
import app.model.entity.Faculty;
import app.model.entity.Specialty;
import app.model.mapper.SpecialtyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final FacultyService facultyService;
    private final SpecialtyMapper specialtyMapper;

    public SpecialtyResponse findById(String publicId) {
        Specialty specialty = specialtyRepository.findByPublicIdAndActiveTrue(publicId);
        checkSpecialtyExists(specialty);
        return specialtyMapper.entityToResponse(specialty);
    }

    public List<SpecialtyResponse> findSpecialities() {
        return specialtyRepository
                .findAllByActiveTrue()
                .stream()
                .map(specialtyMapper::entityToResponse)
                .toList();
    }


    public SpecialtyResponse createSpecialty(SpecialtyRequest specialtyRequest) {
        Specialty specialty = specialtyRepository.findByCodeAndActiveTrue(specialtyRequest.getCode());
        if (specialty != null) {
            throw new SpecialtyException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        specialty = specialtyMapper.requestToEntity(specialtyRequest);
        Faculty faculty = facultyService.findEntityByPublicId(specialtyRequest.getFacultyId());
        specialty.setFaculty(faculty);
        specialty.setPublicId(UUID.randomUUID().toString());
        specialtyRepository.save(specialty);
        return specialtyMapper.entityToResponse(specialty);
    }


    public SpecialtyResponse updateSpecialty(SpecialtyRequest specialtyRequest, String publicId) {
        Specialty specialty = specialtyRepository.findByPublicIdAndActiveTrue(publicId);
        checkSpecialtyExists(specialty);
        Faculty faculty = facultyService.findEntityByPublicId(specialtyRequest.getFacultyId());
        specialty.setFaculty(faculty);
        specialty.setCode(specialtyRequest.getCode());
        specialty.setName(specialtyRequest.getName());
        specialtyRepository.save(specialty);
        return specialtyMapper.entityToResponse(specialty);
    }


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

    public Specialty findEntityByPublicId(String publicId) {
        Specialty specialty = specialtyRepository.findByPublicIdAndActiveTrue(publicId);
        checkSpecialtyExists(specialty);
        return specialty;
    }

    public List<SpecialtyResponse> findSpecialitiesByFacultyId(String facultyId) {
        Faculty faculty = facultyService.findEntityByPublicId(facultyId);
        List<Specialty> specialties = specialtyRepository.findByFacultyAndActiveTrue(faculty);
        return specialties.stream()
                .map(specialtyMapper::entityToResponse)
                .toList();
    }
}

