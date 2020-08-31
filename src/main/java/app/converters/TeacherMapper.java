package app.converters;

import app.dto.request.TeacherRequest;
import app.dto.response.TeacherResponse;
import app.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherResponse entityToResponse(Teacher teacher);

    Teacher requestToEntity(TeacherRequest teacherRequest);

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
}
