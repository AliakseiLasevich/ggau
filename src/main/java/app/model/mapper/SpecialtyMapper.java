package app.model.mapper;

import app.model.dto.request.SpecialtyRequest;
import app.model.dto.response.SpecialtyResponse;
import app.model.entity.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecialtyMapper {

    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

    SpecialtyResponse entityToResponse(Specialty specialty);

    Specialty requestToEntity(SpecialtyRequest specialtyRequest);

}
