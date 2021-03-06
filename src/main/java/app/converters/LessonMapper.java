package app.converters;

import app.dto.request.LessonRequest;
import app.dto.response.LessonResponse;
import app.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    Lesson requestToEntity(LessonRequest lessonRequest);

    LessonResponse entityToResponse(Lesson lesson);

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

}
