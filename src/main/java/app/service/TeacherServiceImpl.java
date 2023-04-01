package app.service;

import app.model.mapper.TeacherMapper;
import app.dao.interfaces.TeacherRepository;
import app.model.dto.request.TeacherRequest;
import app.model.dto.response.TeacherResponse;
import app.model.entity.Cathedra;
import app.model.entity.Teacher;
import app.exception.ErrorMessages;
import app.exception.TeacherException;
import app.service.interfaces.CathedraService;
import app.service.interfaces.TeacherService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    private CathedraService cathedraService;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public TeacherResponse findById(String publicId) {
        Teacher teacher = teacherRepository.findByPublicIdAndActiveTrue(publicId);
        if (teacher == null) {
            throw new TeacherException(ErrorMessages.NO_TEACHER_FOUND.getErrorMessage());
        }
        return teacherMapper.entityToResponse(teacher);
    }

    @Transactional
    @Override
    public List<TeacherResponse> findAll() {
        List<Teacher> teachers = teacherRepository.findAllWithCathedras();
        return teachers.stream().
                map(teacherMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = teacherMapper.requestToEntity(teacherRequest);
        teacher.setPublicId(UUID.randomUUID().toString());
        Cathedra cathedra = cathedraService.findByPublicId(teacherRequest.getCathedraId());
        teacher.setCathedra(cathedra);
        teacherRepository.save(teacher);
        return teacherMapper.entityToResponse(teacher);
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
        return teacherMapper.entityToResponse(teacher);
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