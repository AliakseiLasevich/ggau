package service;

import dao.interfaces.CathedraRepository;
import dao.interfaces.TeacherRepository;
import dto.TeacherDto;
import entity.Teacher;
import mappers.TeacherMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.interfaces.TeacherService;

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
                .peek(teacher -> Hibernate.unproxy(teacher.getCathedra().getFaculty()))  //unproxy cathedra and faculty from lazy loading
                .map(TeacherMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public void postTeacher(TeacherDto teacherDto) {
        System.out.println(teacherDto);
    }
}