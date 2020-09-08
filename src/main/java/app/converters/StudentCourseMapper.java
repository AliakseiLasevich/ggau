package app.converters;

import app.dto.response.StudentCourseResponse;
import app.entity.StudentCourse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentCourseMapper {

    StudentCourseResponse entityToResponse(StudentCourse studentCourse);

    StudentCourseMapper INSTANCE = Mappers.getMapper(StudentCourseMapper.class);
}
