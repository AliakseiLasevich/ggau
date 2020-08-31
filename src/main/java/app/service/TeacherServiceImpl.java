package app.service;

import app.dao.interfaces.TeacherRepository;
import app.dto.request.TeacherRequest;
import app.dto.response.TeacherResponse;
import app.entity.Cathedra;
import app.entity.Teacher;
import app.exception.ErrorMessages;
import app.exception.TeacherException;
import app.converters.TeacherMapper;
import app.service.interfaces.CathedraService;
import app.service.interfaces.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {


    private TeacherRepository teacherRepository;

    private CathedraService cathedraService;

    public TeacherServiceImpl(TeacherRepository teacherRepository, CathedraService cathedraService) {
        this.teacherRepository = teacherRepository;
        this.cathedraService = cathedraService;
    }

    @Override
    public TeacherResponse findById(String publicId) {
        Teacher teacher = teacherRepository.findByPublicIdAndActiveTrue(publicId);
        if (teacher == null) {
            throw new TeacherException(ErrorMessages.NO_TEACHER_FOUND.getErrorMessage());
        }
        return TeacherMapper.INSTANCE.entityToResponse(teacher);
    }

    @Transactional
    @Override
    public List<TeacherResponse> findAll() {
        List<Teacher> teachers = teacherRepository.findAllWithCathedras();
        return teachers.stream().
                map(TeacherMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = TeacherMapper.INSTANCE.requestToEntity(teacherRequest);
        teacher.setPublicId(UUID.randomUUID().toString());
        Cathedra cathedra = cathedraService.findByPublicId(teacherRequest.getCathedraId());
        teacher.setCathedra(cathedra);
        teacherRepository.save(teacher);
        return TeacherMapper.INSTANCE.entityToResponse(teacher);
    }


    @Override
    public TeacherResponse updateTeacher(TeacherRequest teacherRequest, String publicId) {
        Teacher teacher = teacherRepository.findByPublicIdAndActiveTrue(publicId);
        if (teacher == null) {
            throw new TeacherException(ErrorMessages.NO_TEACHER_FOUND.getErrorMessage());
        }
        Cathedra cathedra = cathedraService.findByPublicId(teacherRequest.getCathedraId());
        teacher.setCathedra(cathedra);
        teacher.setName(teacherRequest.getName());
        teacherRepository.save(teacher);
        return TeacherMapper.INSTANCE.entityToResponse(teacher);
    }

    @Override
    public void deleteTeacher(String publicId) {
        Teacher teacher = teacherRepository.findByPublicIdAndActiveTrue(publicId);
        if (teacher == null) {
            throw new TeacherException(ErrorMessages.NO_TEACHER_FOUND.getErrorMessage());
        }
        teacher.setActive(false);
        teacherRepository.save(teacher);
    }
}