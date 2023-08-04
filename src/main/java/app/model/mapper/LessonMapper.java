package app.model.mapper;

import app.model.dto.response.LessonResponse;
import app.model.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

//    Lesson requestToEntity(LessonRequest lessonRequest);

    @Mapping(source = "lesson.cabinet.building.name", target = "cabinet.buildingName")
    @Mapping(source = "lesson.cabinet.building.publicId", target = "cabinet.buildingId")
    LessonResponse entityToResponse(Lesson lesson);

}
