package app.converters;

import app.dto.request.TeacherRequest;
import app.dto.response.TeacherResponse;
import app.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(uses = TeacherMapper.class, componentModel = "spring")
public interface TeacherMapper extends AbstractMapper<Teacher, TeacherRequest, TeacherResponse> {
}
