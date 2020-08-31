package app.converters;

import app.dto.request.DisciplinePlanRequest;
import app.dto.request.LessonsPerWeekRequest;
import app.dto.response.DisciplinePlanResponse;
import app.entity.DisciplinePlan;
import app.entity.LessonsPerWeek;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface DisciplinePlanMapper {

    DisciplinePlanResponse entityToResponse(DisciplinePlan disciplinePlan);

    DisciplinePlan requestToEntity(DisciplinePlanRequest disciplinePlanRequest);

    Map<LocalDate, LessonsPerWeek> lessonsPerWeekRequestToEntity(Map<LocalDate, LessonsPerWeekRequest> mapRequest);

    DisciplinePlanMapper INSTANCE = Mappers.getMapper(DisciplinePlanMapper.class);
}
