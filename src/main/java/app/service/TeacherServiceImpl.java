package app.service;

import app.dao.interfaces.CathedraRepository;
import app.dao.interfaces.TeacherRepository;
import app.dto.TeacherDto;
import app.entity.Cathedra;
import app.entity.Teacher;
import app.mappers.TeacherMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.service.interfaces.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CathedraRepository cathedraRepository;

    @Transactional
    @Override
    public List<TeacherDto> findAll(int page, int limit) {
        if (page > 0) page -= 1;
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<Teacher> teachersPage = teacherRepository.findAll(pageableRequest);
        List<Teacher> teacherEntities = teachersPage.getContent();
        List<TeacherDto> dtos = teacherEntities.stream()
                .peek(teacher -> Hibernate.unproxy(teacher.getCathedra().getFaculty()))  //unproxy cathedra and faculty from lazy loadingСергей Сергеев
                .map(TeacherMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    @Override
    public void postTeacher(TeacherDto teacherDto) {
        Teacher teacher = TeacherMapper.INSTANCE.dtoToEntity(teacherDto);
        Cathedra c = cathedraRepository.findById(teacherDto.getCathedraId()).get();
        teacher.setCathedra(c);
        teacherRepository.save(teacher);
    }

    @Override
    public TeacherDto findById(Long id) {
        Teacher teacher = teacherRepository.findById(id).get();
        TeacherDto teacherDto = TeacherMapper.INSTANCE.entityToDto(teacher);
        return teacherDto;
    }
}