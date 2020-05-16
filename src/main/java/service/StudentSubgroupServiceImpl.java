package service;

import dao.interfaces.StudentGroupRepository;
import dao.interfaces.StudentSubgroupRepository;
import dto.StudentGroupDto;
import dto.StudentSubgroupDto;
import entity.StudentGroup;
import entity.StudentSubgroup;
import exception.StudentGroupException;
import exception.StudentSubgroupException;
import mappers.StudentSubgroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.interfaces.StudentGroupService;
import service.interfaces.StudentSubgroupService;

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
