package app.model.mapper;

import app.model.dto.response.StudentCourseResponse;
import app.model.entity.StudentCourse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentCourseMapper {

    StudentCourseMapper INSTANCE = Mappers.getMapper(StudentCourseMapper.class);

    StudentCourseResponse entityToResponse(StudentCourse studentCourse);
}
