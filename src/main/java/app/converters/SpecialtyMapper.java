package app.converters;

import app.dto.request.SpecialtyRequest;
import app.dto.response.SpecialtyResponse;
import app.entity.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecialtyMapper {

    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

    SpecialtyResponse entityToResponse(Specialty specialty);

    Specialty requestToEntity(SpecialtyRequest specialtyRequest);

}
