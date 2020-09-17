package app.service;

import app.converters.LearnPlanMapper;
import app.dao.interfaces.LearnPlanRepository;
import app.dto.request.LearnPlanRequest;
import app.dto.response.LearnPlanResponse;
import app.entity.*;
import app.exception.ErrorMessages;
import app.exception.LearnPlanException;
import app.service.interfaces.DisciplineService;
import app.service.interfaces.FacultyService;
import app.service.interfaces.LearnPlanService;
import app.service.interfaces.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LearnPlanServiceImpl implements LearnPlanService {

    private final LearnPlanRepository learnPlanRepository;
    private final FacultyService facultyService;
    private final SpecialtyService specialtyService;
    private final DisciplineService disciplineService;

    @Autowired
    public LearnPlanServiceImpl(LearnPlanRepository learnPlanRepository, FacultyService facultyService, SpecialtyService specialtyService, DisciplineService disciplineService) {
        this.learnPlanRepository = learnPlanRepository;
        this.facultyService = facultyService;
        this.specialtyService = specialtyService;
        this.disciplineService = disciplineService;
    }

    //TODO REFACTORING
    @Override
    @Transactional
    public LearnPlanResponse createLearnPlan(LearnPlanRequest learnPlanRequest) {
        LearnPlan learnPlan = LearnPlanMapper.INSTANCE.requestToEntity(learnPlanRequest);
        // УСТАНОВИТЬ АЙДИШНИК ДЛЯ ПЛАНА
        learnPlan.setPublicId(UUID.randomUUID().toString());
        // УСТАНОВИТЬ ФАКУЛЬТЕТ
        Faculty faculty = facultyService.findEntityByPublicId(learnPlanRequest.getFacultyId());
        learnPlan.setFaculty(faculty);
        // УСТАНОВИТЬ СПЕЦИАЛИЗАЦИЮ
        Specialty specialty = specialtyService.findEntityByPublicId(learnPlanRequest.getSpecialtyId());
        learnPlan.setSpecialty(specialty);
        // ДОБАВИТЬ ДЛЯ КАЖДОГО ПЛАНА ДИСЦИПЛИНЫ СВОЙ АЙДИШНИК
        learnPlan.getDisciplinePlan().forEach(disciplinePlan -> disciplinePlan.setPublicId(UUID.randomUUID().toString()));
        // УСТАНОВИТЬ ДВОЙНУЮ СВЯЗЬ ДЛЯ ПРАВИЛЬНОГО МАППИНГА ONE-TO-MANY-MANY-TO-ONE
        List<DisciplinePlan> disciplinePlanList = learnPlan.getDisciplinePlan();
        disciplinePlanList.forEach(disciplinePlan -> disciplinePlan.setLearnPlan(learnPlan));
        // УСТАНОВИТЬ ДЛЯ ПЛАНА ДИСЦИПЛИНЫ СООТВЕТСТВУЮЩУЮ ДИСЦИПЛИНУ
        for (int i = 0; i < disciplinePlanList.size(); i++) {
            Discipline discipline = disciplineService.findEntityByPublicId(learnPlanRequest.getDisciplinePlan().get(i).getDisciplinePublicId());
            DisciplinePlan disciplinePlan = disciplinePlanList.get(i);
            disciplinePlan.setDiscipline(discipline);
            disciplinePlanList.set(i, disciplinePlan);
        }
        // ЗАСЕТАТЬ ДИСЦИПЛИНЫ К ПЛАНУ
        learnPlan.setDisciplinePlan(disciplinePlanList);
        // СОХРАНИТЬ
        learnPlanRepository.save(learnPlan);
        return LearnPlanMapper.INSTANCE.entityToResponse(learnPlan);
    }

    @Override
    public LearnPlanResponse getLearnPlanByPublicId(String publicId) {
        LearnPlan learnPlan = learnPlanRepository.findByPublicId(publicId);
        checkLearnPlanExists(learnPlan);
        return LearnPlanMapper.INSTANCE.entityToResponse(learnPlan);
    }

    private void checkLearnPlanExists(LearnPlan learnPlan) {
        if (learnPlan == null) {
            throw new LearnPlanException(ErrorMessages.NO_LEARN_PLAN_FOUND.getErrorMessage());
        }
    }

    @Override
    public List<LearnPlanResponse> getLearnPlansByDateInclude(LocalDate date) {
        List<LearnPlan> learnPlans = learnPlanRepository.findByDateInclude(date);
        return learnPlans.stream()
                .map(LearnPlanMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<LearnPlanResponse> getAllLearnPlans() {
        List<LearnPlan> learnPlans = learnPlanRepository.findAllByActiveTrue();
        return learnPlans.stream()
                .map(LearnPlanMapper.INSTANCE::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLearnPlan(String publicId) {
        LearnPlan learnPlan = learnPlanRepository.findByPublicId(publicId);
        checkLearnPlanExists(learnPlan);
        learnPlan.setActive(false);
        learnPlanRepository.save(learnPlan);
    }
}
