//package app.service;
//
//import app.dao.interfaces.LearnPlanRepository;
//import app.exception.ErrorMessages;
//import app.exception.LearnPlanException;
//import app.model.dto.request.LearnPlanRequest;
//import app.model.dto.response.LearnPlanResponse;
//import app.model.entity.Faculty;
//import app.model.entity.LearnPlan;
//import app.model.mapper.LearnPlanMapper;
//import app.service.interfaces.SpecialtyService;
//import app.service.interfaces.StudentCourseService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class LearnPlanService {
//
//    private final LearnPlanRepository learnPlanRepository;
//    private final FacultyService facultyService;
//    private final SpecialtyService specialtyService;
//    private final DisciplineService disciplineService;
//    private final StudentCourseService studentCourseService;
//
//
//
//    @Transactional
//    public LearnPlanResponse createLearnPlan(LearnPlanRequest learnPlanRequest) {
//        LearnPlan learnPlan = LearnPlanMapper.INSTANCE.requestToEntity(learnPlanRequest);
//        // УСТАНОВИТЬ АЙДИШНИК ДЛЯ ПЛАНА
////        learnPlan.setPublicId(UUID.randomUUID().toString());
//        // УСТАНОВИТЬ ФАКУЛЬТЕТ
//        Faculty faculty = facultyService.findEntityByPublicId(learnPlanRequest.getFacultyId());
////        learnPlan.setFaculty(faculty);
//        // УСТАНОВИТЬ КОНКРЕТНЫЙ КУРС К ПЛАНУ
////        StudentCourse studentCourse = studentCourseService.findEntityByPublicId(learnPlanRequest.getStudentsCourseId());
////        learnPlan.setStudentCourse(studentCourse);
//        // ДОБАВИТЬ ДЛЯ КАЖДОГО ПЛАНА ДИСЦИПЛИНЫ СВОЙ АЙДИШНИК
////        learnPlan.getDisciplinePlan().forEach(disciplinePlan -> disciplinePlan.setPublicId(UUID.randomUUID().toString()));
//        // УСТАНОВИТЬ ДВОЙНУЮ СВЯЗЬ ДЛЯ ПРАВИЛЬНОГО МАППИНГА ONE-TO-MANY-MANY-TO-ONE
////        List<DisciplinePlan> disciplinePlanList = learnPlan.getDisciplinePlan();
////        disciplinePlanList.forEach(disciplinePlan -> disciplinePlan.setLearnPlan(learnPlan));
//        // УСТАНОВИТЬ ДЛЯ ПЛАНА ДИСЦИПЛИНЫ СООТВЕТСТВУЮЩУЮ ДИСЦИПЛИНУ
////        for (int i = 0; i < disciplinePlanList.size(); i++) {
////            Discipline discipline = disciplineService.findEntityByPublicId(learnPlanRequest.getDisciplinePlan().get(i).getDisciplinePublicId());
////            DisciplinePlan disciplinePlan = disciplinePlanList.get(i);
////            disciplinePlan.setDiscipline(discipline);
////            disciplinePlanList.set(i, disciplinePlan);
////        }
////        // ЗАСЕТАТЬ ДИСЦИПЛИНЫ К ПЛАНУ
////        learnPlan.setDisciplinePlan(disciplinePlanList);
////        // СОХРАНИТЬ
//        learnPlanRepository.save(learnPlan);
//        return LearnPlanMapper.INSTANCE.entityToResponse(learnPlan);
//    }
//
//    public LearnPlanResponse getLearnPlanByPublicId(String publicId) {
//        LearnPlan learnPlan = learnPlanRepository.findByPublicId(publicId);
//        checkLearnPlanExists(learnPlan);
//        return LearnPlanMapper.INSTANCE.entityToResponse(learnPlan);
//    }
//
//    private void checkLearnPlanExists(LearnPlan learnPlan) {
//        if (learnPlan == null) {
//            throw new LearnPlanException(ErrorMessages.NO_LEARN_PLAN_FOUND.getErrorMessage());
//        }
//    }
//
//    public List<LearnPlanResponse> getLearnPlansByDateInclude(LocalDate date) {
//        List<LearnPlan> learnPlans = learnPlanRepository.findByDateInclude(date);
//        return learnPlans.stream()
//                .map(LearnPlanMapper.INSTANCE::entityToResponse)
//                .toList();
//    }
//
//    public List<LearnPlanResponse> getAllLearnPlans() {
//        List<LearnPlan> learnPlans = learnPlanRepository.findAllByActiveTrue();
//        return learnPlans.stream()
//                .map(LearnPlanMapper.INSTANCE::entityToResponse)
//                .toList();
//    }
//
//    public void deleteLearnPlan(String publicId) {
//        LearnPlan learnPlan = learnPlanRepository.findByPublicId(publicId);
//        checkLearnPlanExists(learnPlan);
////        learnPlan.setActive(false);
//        learnPlanRepository.save(learnPlan);
//    }
//
//    public LearnPlanResponse getLearnPlanByDateAndStudentCourse(LocalDate date, String courseId) {
//        LearnPlan learnPlan = learnPlanRepository.findByDateIncludeAndStudentCourse(date, courseId);
//        if (learnPlan == null) {
//            throw new LearnPlanException(ErrorMessages.NO_LEARN_PLAN_FOUND.getErrorMessage());
//        }
//        return LearnPlanMapper.INSTANCE.entityToResponse(learnPlan);
//    }
//}
