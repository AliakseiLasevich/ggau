package app.converters;

import app.dto.request.FacultyRequest;
import app.dto.response.FacultyResponse;
import app.entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    Faculty requestToEntity(FacultyRequest facultyRequest);

    FacultyResponse entityToResponse(Faculty faculty);

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

}
