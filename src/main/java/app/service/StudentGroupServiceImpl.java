package app.service;

import app.dao.interfaces.StudentGroupRepository;
import app.dto.StudentGroupDto;
import app.entity.StudentGroup;
import app.exception.StudentGroupException;
import app.mappers.StudentGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.service.interfaces.SpecialtyService;
import app.service.interfaces.StudentGroupService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    @Autowired
    StudentGroupRepository studentGroupRepository;

    @Autowired
    SpecialtyService specialtyService;

    @Transactional
    @Override
    public List<StudentGroupDto> findAllWithSpecialtyAndSubgroups() {

        List<StudentGroup> studentGroups = studentGroupRepository.findAll();

        List<StudentGroupDto> returnValue = studentGroups
                .stream()
                .map(studentGroup -> {
                    StudentGroupDto studentGroupDto = StudentGroupMapper.INSTANCE.entityToDto(studentGroup);
                    return studentGroupDto;
                })
                .collect(Collectors.toList());
        return returnValue;
    }

    @Transactional
    @Override
    public StudentGroupDto findById(Long id) {
        return StudentGroupMapper.INSTANCE.entityToDto(studentGroupRepository
                .findById(id)
                .orElseThrow(() -> new StudentGroupException("No such group")));
    }

    @Transactional
    @Override
    public void save(StudentGroupDto studentGroupDto) {
        studentGroupRepository.save(StudentGroupMapper.INSTANCE.dtoToEntity(studentGroupDto));
    }

}
