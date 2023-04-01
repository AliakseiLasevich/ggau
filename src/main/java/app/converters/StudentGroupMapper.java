package app.converters;

import app.dto.request.StudentGroupRequest;
import app.dto.response.StudentGroupResponse;
import app.entity.StudentGroup;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentGroupMapper {

    StudentGroupMapper INSTANCE = Mappers.getMapper(StudentGroupMapper.class);

    StudentGroupResponse entityToResponse(StudentGroup studentGroup);

    StudentGroup requestToEntity(StudentGroupRequest studentGroupRequest);
}
