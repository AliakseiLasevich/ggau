package app.converters;

import app.dto.request.DisciplineRequest;
import app.dto.response.DisciplineResponse;
import app.entity.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {

    DisciplineResponse entityToResponse(Discipline discipline);

    Discipline requestToEntity(DisciplineRequest disciplineRequest);


    DisciplineMapper INSTANCE = Mappers.getMapper(DisciplineMapper.class);
}
