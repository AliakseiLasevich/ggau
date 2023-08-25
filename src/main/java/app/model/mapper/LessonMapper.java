package app.model.mapper;

import app.model.dto.request.LessonRequest;
import app.model.dto.response.LessonResponse;
import app.model.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonMapper extends AbstractMapper<Lesson, LessonRequest, LessonResponse> {


}
