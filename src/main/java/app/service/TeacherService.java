package app.service;

import app.dao.interfaces.TeacherRepository;
import app.exception.TeacherException;
import app.exception.errors.ErrorMessage;
import app.model.dto.request.TeacherRequest;
import app.model.dto.response.TeacherResponse;
import app.model.entity.Cathedra;
import app.model.entity.Teacher;
import app.model.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Setter
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CathedraService cathedraService;
    private final TeacherMapper teacherMapper;

    public TeacherResponse findById(String publicId) {
        Teacher teacher = findEntityByPublicId(publicId);
        return teacherMapper.entityToResponse(teacher);
    }

    Teacher findEntityByPublicId(String publicId) {
        return teacherRepository.findByPublicIdAndActiveTrue(publicId)
                .orElseThrow(() -> {
                    log.error("Cabinet not found: {}", publicId);
                    throw new TeacherException("Cabinet not found: " + publicId);
                });
    }

    @Transactional
    public List<TeacherResponse> findAll() {
        List<Teacher> teachers = teacherRepository.findAllWithCathedras();
        return teachers.stream().
                map(teacherMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = teacherMapper.requestToEntity(teacherRequest);
        teacher.setPublicId(UUID.randomUUID().toString());
        Cathedra cathedra = cathedraService.findByPublicId(teacherRequest.getCathedraId());
        teacher.setCathedra(cathedra);
        teacherRepository.save(teacher);
        return teacherMapper.entityToResponse(teacher);
    }


    public TeacherResponse updateTeacher(TeacherRequest teacherRequest, String publicId) {
        Teacher teacher = findEntityByPublicId(publicId);
        Cathedra cathedra = cathedraService.findByPublicId(teacherRequest.getCathedraId());
        teacher.setCathedra(cathedra);
        teacher.setName(teacherRequest.getName());
        teacherRepository.save(teacher);
        return teacherMapper.entityToResponse(teacher);
    }

    public void deleteTeacher(String publicId) {
        Teacher teacher = findEntityByPublicId(publicId);
        teacher.setActive(false);
        teacherRepository.save(teacher);
    }

    public void validateTeacherOverlapping(String id, int orderNumber, LocalDate date, List<ErrorMessage> errorMessages) {
        Optional<Teacher> teacherOptional = teacherRepository.findByParameters(id, orderNumber, date);
        teacherOptional.ifPresent(teacher -> {
            log.error("Преподаватель с указанными параметрами уже занят {}", teacher);
            errorMessages.add(new ErrorMessage("Преподаватель с указанными параметрами уже занят"));
        });
    }
}