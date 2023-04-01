package app.converters;

import app.dto.request.FacultyRequest;
import app.dto.response.FacultyResponse;
import app.entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = FacultyMapper.class, componentModel = "spring")
public interface FacultyMapper extends AbstractMapper<Faculty, FacultyRequest, FacultyResponse> {

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);
}
