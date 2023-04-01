package app.model.mapper;

import app.model.dto.request.DisciplinePlanRequest;
import app.model.dto.request.LearnPlanRequest;
import app.model.dto.request.LessonsPerWeekRequest;
import app.model.dto.response.DisciplineResponse;
import app.model.dto.response.LearnPlanResponse;
import app.model.entity.DisciplinePlan;
import app.model.entity.LearnPlan;
import app.model.entity.LessonsPerWeek;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LearnPlanMapper {

    LearnPlanMapper INSTANCE = Mappers.getMapper(LearnPlanMapper.class);

    LearnPlanResponse entityToResponse(LearnPlan learnPlan);

    LearnPlan requestToEntity(LearnPlanRequest learnPlanRequest);

    DisciplinePlan disciplinePlanRequestToEntity(DisciplinePlanRequest disciplinePlanRequest);

    Map<LocalDate, LessonsPerWeek> lessonsPerWeekRequestToEntity(Map<LocalDate, LessonsPerWeekRequest> mapRequest);

    DisciplineResponse disciplinePlanEntityToResponse(DisciplinePlan disciplinePlan);
}
