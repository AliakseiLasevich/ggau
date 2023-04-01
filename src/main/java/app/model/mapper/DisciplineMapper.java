package app.model.mapper;

import app.model.dto.request.DisciplineRequest;
import app.model.dto.response.DisciplineResponse;
import app.model.entity.Discipline;
import org.mapstruct.Mapper;

@Mapper(uses = DisciplineMapper.class, componentModel = "spring")
public interface DisciplineMapper extends AbstractMapper<Discipline, DisciplineRequest, DisciplineResponse> {
}
