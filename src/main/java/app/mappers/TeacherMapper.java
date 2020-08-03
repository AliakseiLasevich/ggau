package app.mappers;

import app.dto.TeacherDto;
import app.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import app.dto.request.TeacherRequestModel;
import app.dto.response.TeacherRest;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(source = "active", target = "active")
    TeacherDto entityToDto(Teacher teacher);

    @Mapping(source = "active", target = "active")
    TeacherRest dtoToRest(TeacherDto teacherDto);


    TeacherDto requestToDto(TeacherRequestModel teacherRequestModel);

    @Mapping(source = "active", target = "active")
    Teacher dtoToEntity(TeacherDto teacherDto);

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
}
