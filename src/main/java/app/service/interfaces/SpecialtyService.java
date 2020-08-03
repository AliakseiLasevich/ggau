package app.service.interfaces;

import app.dto.SpecialtyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecialtyService {

    SpecialtyDto findById(Long id);

    List<SpecialtyDto> findSpecialities();

    void save(SpecialtyDto specialtyDto);
}
