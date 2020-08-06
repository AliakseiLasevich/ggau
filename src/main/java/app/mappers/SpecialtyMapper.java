package app.mappers;

import app.dto.SpecialtyDto;
import app.entity.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import app.dto.request.SpecialtyRequestModel;
import app.dto.response.SpecialtyRest;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    @Mapping(source = "active", target = "active")
    @Mapping(source = "faculty.id", target = "facultyId")
    SpecialtyDto entityToDto(Specialty speciality);

    @Mapping(source = "active", target = "active")
    Specialty dtoToEntity(SpecialtyDto specialtyDto);

    @Mapping(source = "active", target = "active")
    SpecialtyRest dtoToRest(SpecialtyDto specialtyDto);

    @Mapping(source = "active", target = "active")
    SpecialtyDto requestToDto(SpecialtyRequestModel requestModel);

    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

}