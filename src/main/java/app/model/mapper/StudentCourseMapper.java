package app.model.mapper;

import app.model.dto.request.StudentCourseRequest;
import app.model.dto.response.StudentCourseResponse;
import app.model.entity.StudentCourse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = StudentCourse.class)
public interface StudentCourseMapper extends AbstractMapper<StudentCourse, StudentCourseRequest, StudentCourseResponse> {

    @Override
    @Mapping(source = "specialty.publicId", target = "specialtyPublicId")
    StudentCourseResponse entityToResponse(StudentCourse entity);
}
