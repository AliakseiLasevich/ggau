package service.interfaces;

import dto.StudentSubgroupDto;

public interface StudentSubgroupService {
    StudentSubgroupDto findById(Long id);

    void save(StudentSubgroupDto studentSubgroupDto);
}
