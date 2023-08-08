package app.service;

import app.dao.interfaces.LessonRepository;
import app.exception.LessonException;
import app.exception.errors.ErrorMessage;
import app.model.dto.request.LessonRequest;
import app.model.dto.response.LessonResponse;
import app.model.entity.Cabinet;
import app.model.entity.Discipline;
import app.model.entity.Lesson;
import app.model.entity.StudentSubgroup;
import app.model.entity.Teacher;
import app.model.mapper.LessonMapper;
import app.service.interfaces.StudentCourseService;
import app.utils.PublicIdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    private final CabinetService cabinetService;
    private final TeacherService teacherService;
    private final DisciplineService disciplineService;
    private final StudentCourseService studentCourseService;

    private final StudentSubgroupService studentSubgroupService;
    private final LessonMapper lessonMapper;
    private final ObjectMapper objectMapper;


    @Transactional
    public LessonResponse createLesson(LessonRequest lessonRequest) throws JsonProcessingException {
        int lessonRequestOrderNumber = lessonRequest.getOrderNumber();
        LocalDate date = lessonRequest.getDate();
        List<ErrorMessage> errorMessages = new ArrayList<>();

        Cabinet cabinet = getCabinet(lessonRequest, lessonRequestOrderNumber, date, errorMessages);
        Teacher teacher = getTeacher(lessonRequest, lessonRequestOrderNumber, date, errorMessages);
        List<StudentSubgroup> studentSubgroups = getStudentSubgroups(lessonRequest, lessonRequestOrderNumber, date, errorMessages);
        Discipline discipline = disciplineService.findEntityByPublicId(lessonRequest.getDisciplineId());

        Lesson lesson = Lesson.builder()
                .cabinet(cabinet)
                .teacher(teacher)
                .discipline(discipline)
                .orderNumber(lessonRequestOrderNumber)
                .date(date)
                .type(lessonRequest.getType())
                .studentSubgroups(studentSubgroups)
                .active(true)
                .publicId(PublicIdGenerator.generatePublicId())
                .build();

        if (!errorMessages.isEmpty()) {
            throw new LessonException(objectMapper.writeValueAsString(errorMessages));
        }
        lessonRepository.save(lesson);
        return lessonMapper.entityToResponse(lesson);
    }

    private Teacher getTeacher(LessonRequest lessonRequest, int lessonRequestOrderNumber, LocalDate date, List<ErrorMessage> errorMessages) {
        Teacher teacher = teacherService.findEntityByPublicId(lessonRequest.getTeacherId());
        teacherService.validateTeacherOverlapping(teacher.getId(), lessonRequestOrderNumber, date, errorMessages);
        return teacher;
    }

    private Cabinet getCabinet(LessonRequest lessonRequest, int lessonRequestOrderNumber, LocalDate date, List<ErrorMessage> errorMessages) {
        Cabinet cabinet = cabinetService.findEntityByPublicId(lessonRequest.getCabinetId());
        cabinetService.validateCabinetOverlapping(cabinet.getId(), lessonRequestOrderNumber, date, errorMessages);
        return cabinet;
    }

    private List<StudentSubgroup> getStudentSubgroups(LessonRequest lessonRequest, int lessonRequestOrderNumber, LocalDate date, List<ErrorMessage> errorMessages) {
        List<StudentSubgroup> studentSubgroups = studentSubgroupService.findEntitiesByPublicIds(lessonRequest.getStudentSubgroupIds());
        if (studentSubgroups.isEmpty()) {
            log.error("Подгруппы не найдены: {}", lessonRequest.getStudentSubgroupIds());
            throw new LessonException("Подгруппы c указанными id не найдены");
        }
        List<String> studentSubgroupIds = studentSubgroups.stream().map(StudentSubgroup::getId).toList();
        List<Lesson> lessons = findLessonsByParams(studentSubgroupIds, lessonRequestOrderNumber, date);
        if (!lessons.isEmpty()) {
            log.error("Подгруппы указанных студентов с указанными параметрами уже заняты {}", studentSubgroups);
            errorMessages.add(new ErrorMessage("Подгруппы указанных студентов с указанными параметрами уже заняты"));
        }
        return studentSubgroups;
    }


    public List<LessonResponse> getLessonsBetweenDates(LocalDate firstDate, LocalDate lastDate) {
        List<Lesson> lessons = lessonRepository.findAllLessonsBetweenDates(firstDate, lastDate);
        return lessons.stream().map(LessonMapper.INSTANCE::entityToResponse).toList();
    }

    List<Lesson> findLessonsByParams(List<String> studentSubgroupIds, int orderNumber, LocalDate date) {
        return lessonRepository.findLessonsByParams(studentSubgroupIds, orderNumber, date);
    }
}
