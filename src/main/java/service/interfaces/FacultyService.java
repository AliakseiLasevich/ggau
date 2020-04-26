package service.interfaces;

import dto.FacultyDto;
import entity.Faculty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacultyService {
    List<Faculty> findAll(int page, int limit);
    Faculty findById(Long id);
    FacultyDto createFaculty(FacultyDto facultyDto);

}
