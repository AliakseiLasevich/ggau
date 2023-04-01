package app.converters;

import app.dto.request.DisciplineRequest;
import app.dto.response.DisciplineResponse;
import app.entity.Discipline;
import org.mapstruct.Mapper;

@Mapper(uses = DisciplineMapper.class, componentModel = "spring")
public interface DisciplineMapper extends AbstractMapper<Discipline, DisciplineRequest, DisciplineResponse> {
}
