package app.converters;

import app.dto.request.StudentGroupRequest;
import app.dto.response.StudentGroupResponse;
import app.entity.StudentGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentGroupMapper {

    StudentGroupResponse entityToResponse(StudentGroup studentGroup);

    StudentGroup requestToEntity(StudentGroupRequest studentGroupRequest);

    StudentGroupMapper INSTANCE = Mappers.getMapper(StudentGroupMapper.class);
}
