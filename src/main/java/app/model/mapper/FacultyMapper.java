package app.model.mapper;

import app.model.dto.request.FacultyRequest;
import app.model.dto.response.FacultyResponse;
import app.model.entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = FacultyMapper.class, componentModel = "spring")
public interface FacultyMapper extends AbstractMapper<Faculty, FacultyRequest, FacultyResponse> {

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);
}
