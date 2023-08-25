package app.model.mapper;

import app.model.dto.request.StudentSubgroupRequest;
import app.model.dto.response.StudentSubgroupResponse;
import app.model.entity.StudentSubgroup;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentSubgroupMapper extends AbstractMapper<StudentSubgroup, StudentSubgroupRequest, StudentSubgroupResponse> {

}
