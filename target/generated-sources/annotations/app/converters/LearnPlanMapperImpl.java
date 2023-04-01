package app.converters;

import app.model.mapper.LearnPlanMapper;
import app.model.dto.request.DisciplinePlanRequest;
import app.model.dto.request.LearnPlanRequest;
import app.model.dto.request.LessonsPerWeekRequest;
import app.model.dto.response.DisciplinePlanResponse;
import app.model.dto.response.DisciplineResponse;
import app.model.dto.response.FacultyResponse;
import app.model.dto.response.LearnPlanResponse;
import app.model.dto.response.SpecialtyResponse;
import app.model.dto.response.StudentCourseResponse;
import app.model.entity.Discipline;
import app.model.entity.DisciplinePlan;
import app.model.entity.Faculty;
import app.model.entity.LearnPlan;
import app.model.entity.LessonsPerWeek;
import app.model.entity.Specialty;
import app.model.entity.StudentCourse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class LearnPlanMapperImpl implements LearnPlanMapper {

    @Override
    public LearnPlanResponse entityToResponse(LearnPlan learnPlan) {
        if ( learnPlan == null ) {
            return null;
        }

        LearnPlanResponse learnPlanResponse = new LearnPlanResponse();

        learnPlanResponse.setStartDate( learnPlan.getStartDate() );
        learnPlanResponse.setEndDate( learnPlan.getEndDate() );
        learnPlanResponse.setPublicId( learnPlan.getPublicId() );
        learnPlanResponse.setFaculty( facultyToFacultyResponse( learnPlan.getFaculty() ) );
        learnPlanResponse.setStudentCourse( studentCourseToStudentCourseResponse( learnPlan.getStudentCourse() ) );
        learnPlanResponse.setDisciplinePlan( disciplinePlanListToDisciplinePlanResponseList( learnPlan.getDisciplinePlan() ) );

        return learnPlanResponse;
    }

    @Override
    public LearnPlan requestToEntity(LearnPlanRequest learnPlanRequest) {
        if ( learnPlanRequest == null ) {
            return null;
        }

        LearnPlan learnPlan = new LearnPlan();

        learnPlan.setStartDate( learnPlanRequest.getStartDate() );
        learnPlan.setEndDate( learnPlanRequest.getEndDate() );
        learnPlan.setDisciplinePlan( disciplinePlanRequestListToDisciplinePlanList( learnPlanRequest.getDisciplinePlan() ) );

        return learnPlan;
    }

    @Override
    public DisciplinePlan disciplinePlanRequestToEntity(DisciplinePlanRequest disciplinePlanRequest) {
        if ( disciplinePlanRequest == null ) {
            return null;
        }

        DisciplinePlan disciplinePlan = new DisciplinePlan();

        disciplinePlan.setHoursSummary( disciplinePlanRequest.getHoursSummary() );
        disciplinePlan.setSummary( disciplinePlanRequest.getSummary() );
        disciplinePlan.setHoursCabinet( disciplinePlanRequest.getHoursCabinet() );
        disciplinePlan.setHoursLecture( disciplinePlanRequest.getHoursLecture() );
        disciplinePlan.setHoursLaboratory( disciplinePlanRequest.getHoursLaboratory() );
        disciplinePlan.setHoursPracticalSeminary( disciplinePlanRequest.getHoursPracticalSeminary() );
        disciplinePlan.setHoursKSRLecture( disciplinePlanRequest.getHoursKSRLecture() );
        disciplinePlan.setHoursKSRLaboratory( disciplinePlanRequest.getHoursKSRLaboratory() );
        disciplinePlan.setHoursKSRPractical( disciplinePlanRequest.getHoursKSRPractical() );
        disciplinePlan.setHoursKSRSummary( disciplinePlanRequest.getHoursKSRSummary() );
        disciplinePlan.setTestCount( disciplinePlanRequest.getTestCount() );
        disciplinePlan.setFlow( disciplinePlanRequest.getFlow() );
        disciplinePlan.setLessons( lessonsPerWeekRequestToEntity( disciplinePlanRequest.getLessons() ) );
        disciplinePlan.setExam( disciplinePlanRequest.isExam() );
        disciplinePlan.setTest( disciplinePlanRequest.isTest() );
        disciplinePlan.setCourseProject( disciplinePlanRequest.isCourseProject() );
        disciplinePlan.setCourseWork( disciplinePlanRequest.isCourseWork() );

        return disciplinePlan;
    }

    @Override
    public Map<LocalDate, LessonsPerWeek> lessonsPerWeekRequestToEntity(Map<LocalDate, LessonsPerWeekRequest> mapRequest) {
        if ( mapRequest == null ) {
            return null;
        }

        Map<LocalDate, LessonsPerWeek> map = new LinkedHashMap<LocalDate, LessonsPerWeek>( Math.max( (int) ( mapRequest.size() / .75f ) + 1, 16 ) );

        for ( java.util.Map.Entry<LocalDate, LessonsPerWeekRequest> entry : mapRequest.entrySet() ) {
            LocalDate key = entry.getKey();
            LessonsPerWeek value = lessonsPerWeekRequestToLessonsPerWeek( entry.getValue() );
            map.put( key, value );
        }

        return map;
    }

    @Override
    public DisciplineResponse disciplinePlanEntityToResponse(DisciplinePlan disciplinePlan) {
        if ( disciplinePlan == null ) {
            return null;
        }

        DisciplineResponse disciplineResponse = new DisciplineResponse();

        disciplineResponse.setPublicId( disciplinePlan.getPublicId() );

        return disciplineResponse;
    }

    protected FacultyResponse facultyToFacultyResponse(Faculty faculty) {
        if ( faculty == null ) {
            return null;
        }

        FacultyResponse facultyResponse = new FacultyResponse();

        facultyResponse.setPublicId( faculty.getPublicId() );
        facultyResponse.setName( faculty.getName() );

        return facultyResponse;
    }

    protected SpecialtyResponse specialtyToSpecialtyResponse(Specialty specialty) {
        if ( specialty == null ) {
            return null;
        }

        SpecialtyResponse specialtyResponse = new SpecialtyResponse();

        specialtyResponse.setPublicId( specialty.getPublicId() );
        specialtyResponse.setName( specialty.getName() );
        specialtyResponse.setCode( specialty.getCode() );
        specialtyResponse.setFaculty( facultyToFacultyResponse( specialty.getFaculty() ) );

        return specialtyResponse;
    }

    protected StudentCourseResponse studentCourseToStudentCourseResponse(StudentCourse studentCourse) {
        if ( studentCourse == null ) {
            return null;
        }

        StudentCourseResponse studentCourseResponse = new StudentCourseResponse();

        studentCourseResponse.setPublicId( studentCourse.getPublicId() );
        studentCourseResponse.setCourseNumber( studentCourse.getCourseNumber() );
        studentCourseResponse.setSpecialty( specialtyToSpecialtyResponse( studentCourse.getSpecialty() ) );

        return studentCourseResponse;
    }

    protected DisciplineResponse disciplineToDisciplineResponse(Discipline discipline) {
        if ( discipline == null ) {
            return null;
        }

        DisciplineResponse disciplineResponse = new DisciplineResponse();

        disciplineResponse.setName( discipline.getName() );
        disciplineResponse.setPublicId( discipline.getPublicId() );

        return disciplineResponse;
    }

    protected DisciplinePlanResponse disciplinePlanToDisciplinePlanResponse(DisciplinePlan disciplinePlan) {
        if ( disciplinePlan == null ) {
            return null;
        }

        DisciplinePlanResponse disciplinePlanResponse = new DisciplinePlanResponse();

        disciplinePlanResponse.setPublicId( disciplinePlan.getPublicId() );
        disciplinePlanResponse.setDiscipline( disciplineToDisciplineResponse( disciplinePlan.getDiscipline() ) );
        disciplinePlanResponse.setHoursSummary( disciplinePlan.getHoursSummary() );
        disciplinePlanResponse.setSummary( disciplinePlan.getSummary() );
        disciplinePlanResponse.setHoursCabinet( disciplinePlan.getHoursCabinet() );
        disciplinePlanResponse.setHoursLecture( disciplinePlan.getHoursLecture() );
        disciplinePlanResponse.setHoursLaboratory( disciplinePlan.getHoursLaboratory() );
        disciplinePlanResponse.setHoursPracticalSeminary( disciplinePlan.getHoursPracticalSeminary() );
        disciplinePlanResponse.setHoursKSRLecture( disciplinePlan.getHoursKSRLecture() );
        disciplinePlanResponse.setHoursKSRLaboratory( disciplinePlan.getHoursKSRLaboratory() );
        disciplinePlanResponse.setHoursKSRPractical( disciplinePlan.getHoursKSRPractical() );
        disciplinePlanResponse.setHoursKSRSummary( disciplinePlan.getHoursKSRSummary() );
        disciplinePlanResponse.setTestCount( disciplinePlan.getTestCount() );
        disciplinePlanResponse.setFlow( disciplinePlan.getFlow() );
        Map<LocalDate, LessonsPerWeek> map = disciplinePlan.getLessons();
        if ( map != null ) {
            disciplinePlanResponse.setLessons( new LinkedHashMap<LocalDate, LessonsPerWeek>( map ) );
        }
        disciplinePlanResponse.setExam( disciplinePlan.isExam() );
        disciplinePlanResponse.setTest( disciplinePlan.isTest() );
        disciplinePlanResponse.setCourseProject( disciplinePlan.isCourseProject() );
        disciplinePlanResponse.setCourseWork( disciplinePlan.isCourseWork() );

        return disciplinePlanResponse;
    }

    protected List<DisciplinePlanResponse> disciplinePlanListToDisciplinePlanResponseList(List<DisciplinePlan> list) {
        if ( list == null ) {
            return null;
        }

        List<DisciplinePlanResponse> list1 = new ArrayList<DisciplinePlanResponse>( list.size() );
        for ( DisciplinePlan disciplinePlan : list ) {
            list1.add( disciplinePlanToDisciplinePlanResponse( disciplinePlan ) );
        }

        return list1;
    }

    protected List<DisciplinePlan> disciplinePlanRequestListToDisciplinePlanList(List<DisciplinePlanRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<DisciplinePlan> list1 = new ArrayList<DisciplinePlan>( list.size() );
        for ( DisciplinePlanRequest disciplinePlanRequest : list ) {
            list1.add( disciplinePlanRequestToEntity( disciplinePlanRequest ) );
        }

        return list1;
    }

    protected LessonsPerWeek lessonsPerWeekRequestToLessonsPerWeek(LessonsPerWeekRequest lessonsPerWeekRequest) {
        if ( lessonsPerWeekRequest == null ) {
            return null;
        }

        LessonsPerWeek lessonsPerWeek = new LessonsPerWeek();

        lessonsPerWeek.setLecture( lessonsPerWeekRequest.getLecture() );
        lessonsPerWeek.setPractical( lessonsPerWeekRequest.getPractical() );
        lessonsPerWeek.setLaboratory( lessonsPerWeekRequest.getLaboratory() );

        return lessonsPerWeek;
    }
}
