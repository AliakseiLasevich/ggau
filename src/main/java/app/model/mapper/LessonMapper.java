package app.model.mapper;

import app.model.dto.request.LessonRequest;
import app.model.dto.response.LessonResponse;
import app.model.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    Lesson requestToEntity(LessonRequest lessonRequest);

    LessonResponse entityToResponse(Lesson lesson);

}
