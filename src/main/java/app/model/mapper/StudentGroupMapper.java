package app.model.mapper;

import app.model.dto.request.StudentGroupRequest;
import app.model.dto.response.StudentGroupResponse;
import app.model.entity.StudentGroup;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentGroupMapper extends AbstractMapper<StudentGroup, StudentGroupRequest, StudentGroupResponse> {


}
