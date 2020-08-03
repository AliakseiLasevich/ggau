package app.service;

import app.dao.interfaces.StudentGroupRepository;
import app.dao.interfaces.StudentSubgroupRepository;
import app.dto.StudentSubgroupDto;
import app.entity.StudentGroup;
import app.entity.StudentSubgroup;
import app.exception.StudentGroupException;
import app.exception.StudentSubgroupException;
import app.mappers.StudentSubgroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.service.interfaces.StudentSubgroupService;

@Service
public class StudentSubgroupServiceImpl implements StudentSubgroupService {

    @Autowired
    private StudentSubgroupRepository studentSubgroupRepository;

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Transactional
    @Override
    public StudentSubgroupDto findById(Long id) {
        return StudentSubgroupMapper.INSTANCE.entityToDto(studentSubgroupRepository.findById(id).orElseThrow(() -> new StudentSubgroupException("Нет такой подгруппы!!!11")));
    }

    @Transactional
    @Override
    public void save(StudentSubgroupDto studentSubgroupDto) {
        StudentSubgroup studentSubgroup = StudentSubgroupMapper.INSTANCE.dtoToEntity(studentSubgroupDto);
        StudentGroup studentGroup = studentGroupRepository.findById(studentSubgroupDto.getStudentGroupId()).orElseThrow(() -> new StudentGroupException("Нет такой группы"));
        studentSubgroup.setStudentGroup(studentGroup);
        studentSubgroupRepository.save(studentSubgroup);
    }


}
