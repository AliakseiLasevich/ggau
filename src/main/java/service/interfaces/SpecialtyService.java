package service.interfaces;

import dto.SpecialtyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecialtyService {

    SpecialtyDto findById(Long id);

    List<SpecialtyDto> findSpecialities();

    void save(SpecialtyDto specialtyDto);
}
