package app.service.interfaces;

import app.dto.StudentSubgroupDto;

public interface StudentSubgroupService {
    StudentSubgroupDto findById(Long id);

    void save(StudentSubgroupDto studentSubgroupDto);
}
