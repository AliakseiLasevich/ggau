package app.mappers;

import app.dto.request.SpecialtyRequest;
import app.dto.response.SpecialtyResponse;
import app.entity.Specialty;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    SpecialtyResponse entityToResponse(Specialty specialty);

    Specialty requestToEntity(SpecialtyRequest specialtyRequest);

    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

}
