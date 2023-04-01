package app.converters;

import app.dto.response.StudentCourseResponse;
import app.entity.StudentCourse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentCourseMapper {

    StudentCourseMapper INSTANCE = Mappers.getMapper(StudentCourseMapper.class);

    StudentCourseResponse entityToResponse(StudentCourse studentCourse);
}
