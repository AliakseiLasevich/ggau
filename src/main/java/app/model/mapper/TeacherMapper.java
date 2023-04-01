package app.model.mapper;

import app.model.dto.request.TeacherRequest;
import app.model.dto.response.TeacherResponse;
import app.model.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(uses = TeacherMapper.class, componentModel = "spring")
public interface TeacherMapper extends AbstractMapper<Teacher, TeacherRequest, TeacherResponse> {
}
