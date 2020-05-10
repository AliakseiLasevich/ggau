package mappers;

import dto.TeacherDto;
import entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import request.TeacherRequestModel;
import response.TeacherRest;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

//    @Mapping(target = "cathedra", source = "teacher.cathedra")
    TeacherDto entityToDto(Teacher teacher);

//    @Mapping(target = "id", source = "teacherDto.id")
    TeacherRest dtoToRest(TeacherDto teacherDto);

    TeacherDto requestToDto(TeacherRequestModel teacherRequestModel);

    Teacher dtoToEntity(TeacherDto teacherDto);

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
}
