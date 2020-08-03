package app.service.interfaces;

import app.dto.FacultyDto;
import app.entity.Faculty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacultyService {
    List<FacultyDto> findAll(int page, int limit);
    Faculty findById(Long id);
    FacultyDto createFaculty(FacultyDto facultyDto);

    void updateFaculty(FacultyDto facultyDto);
}
