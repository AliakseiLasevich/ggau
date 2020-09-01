package app.service;

import app.converters.LearnPlanMapper;
import app.dao.interfaces.LearnPlanRepository;
import app.dto.request.LearnPlanRequest;
import app.dto.response.LearnPlanResponse;
import app.entity.*;
import app.service.interfaces.DisciplineService;
import app.service.interfaces.FacultyService;
import app.service.interfaces.LearnPlanService;
import app.service.interfaces.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

    @Override
    @Transactional
    public LearnPlanResponse createLearnPlan(LearnPlanRequest learnPlanRequest) {

        LearnPlan learnPlan = LearnPlanMapper.INSTANCE.requestToEntity(learnPlanRequest);

        // УСТАНОВИТЬ АЙДИШНИК ДЛЯ ПЛАНА
        learnPlan.setPublicId(UUID.randomUUID().toString());

//        УСТАНОВИТЬ ФАКУЛЬТЕТ
        Faculty faculty = facultyService.findEntityByPublicId(learnPlanRequest.getFacultyId());
        learnPlan.setFaculty(faculty);

//        УСТАНОВИТЬ СПЕЦИАЛИЗАЦИЮ
        Specialty specialty = specialtyService.findEntityByPublicId(learnPlanRequest.getSpecialtyId());
        learnPlan.setSpecialty(specialty);

//        ДОБАВИТЬ ДЛЯ КАЖДОГО ПЛАНА ДИСЦИПЛИНЫ СВОЙ АЙДИШНИК
        learnPlan.getDisciplinePlan().forEach(disciplinePlan -> disciplinePlan.setPublicId(UUID.randomUUID().toString()));

////        УСТАНОВИТЬ ДВОЙНУЮ СВЯЗЬ ДЛЯ ПРАВИЛЬНОГО МАППИНГА ONE-TO-MANY-MANY-TO-ONE
        List<DisciplinePlan> disciplinePlanList = learnPlan.getDisciplinePlan();
        disciplinePlanList.forEach(disciplinePlan -> disciplinePlan.setLearnPlan(learnPlan));


//        УСТАНОВИТЬ ДЛЯ ПЛАНА ДИСЦИПЛИНЫ СООТВЕТСТВУЮЩУЮ ДИСЦИПЛИНУ
        for (int i = 0; i < disciplinePlanList.size(); i++) {
            Discipline discipline = disciplineService.findEntityByPublicId(learnPlanRequest.getDisciplinePlan().get(i).getDisciplinePublicId());
            DisciplinePlan disciplinePlan = disciplinePlanList.get(i);
            disciplinePlan.setDiscipline(discipline);
            disciplinePlanList.set(i, disciplinePlan);
        }

//        ЗАСЕТАТЬ ДИСЦИПЛИНЫ К ПЛАНУ
        learnPlan.setDisciplinePlan(disciplinePlanList);

//        СОХРАНИТЬ
        learnPlanRepository.save(learnPlan);
        return LearnPlanMapper.INSTANCE.entityToResponse(learnPlan);
    }
}
