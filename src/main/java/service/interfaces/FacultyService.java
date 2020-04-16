package service.interfaces;

import dto.FacultyDto;
import entity.Faculty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacultyService {
    List<Faculty> findAll();
    Faculty findById(Long id);
    FacultyDto createFaculty(FacultyDto facultyDto);

}
