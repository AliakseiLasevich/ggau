package app.converters;

import app.dto.request.DisciplinePlanRequest;
import app.dto.request.LearnPlanRequest;
import app.dto.request.LessonsPerWeekRequest;
import app.dto.response.DisciplineResponse;
import app.dto.response.LearnPlanResponse;
import app.entity.Discipline;
import app.entity.DisciplinePlan;
import app.entity.LearnPlan;
import app.entity.LessonsPerWeek;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface LearnPlanMapper {

    LearnPlanResponse entityToResponse(LearnPlan learnPlan);

    LearnPlan requestToEntity(LearnPlanRequest learnPlanRequest);

    DisciplinePlan disciplinePlanRequestToEntity(DisciplinePlanRequest disciplinePlanRequest);

    Map<LocalDate, LessonsPerWeek> lessonsPerWeekRequestToEntity(Map<LocalDate, LessonsPerWeekRequest> mapRequest);

    DisciplineResponse disciplinePlanEntityToResponse(DisciplinePlan disciplinePlan);

    LearnPlanMapper INSTANCE = Mappers.getMapper(LearnPlanMapper.class);
}
