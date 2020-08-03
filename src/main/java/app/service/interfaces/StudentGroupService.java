package app.service.interfaces;

import app.dto.StudentGroupDto;

import java.util.List;

public interface StudentGroupService {

    List<StudentGroupDto> findAllWithSpecialtyAndSubgroups();

    StudentGroupDto findById(Long id);

    void save(StudentGroupDto studentGroupDto);
}
