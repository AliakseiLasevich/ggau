package app.mappers;

import app.dto.FacultyDto;
import app.entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import app.dto.request.FacultyRequestModel;
import app.dto.response.FacultyRest;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    @Mapping(source = "active", target = "active")
    FacultyRest dtoToRest(FacultyDto facultyDto);

    @Mapping(source = "active", target = "active")
    Faculty dtoToEntity(FacultyDto facultyDto);

    @Mapping(source = "active", target = "active")
    FacultyDto entityToDto(Faculty faculty);

    @Mapping(source = "active", target = "active")
    FacultyDto requestModelToDto(FacultyRequestModel requestModel);

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

}
