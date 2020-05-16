package service.interfaces;

import dto.StudentGroupDto;

import java.util.List;

public interface StudentGroupService {

    List<StudentGroupDto> findAllWithSpecialtyAndSubgroups();

    StudentGroupDto findById(Long id);

    void save(StudentGroupDto studentGroupDto);
}
