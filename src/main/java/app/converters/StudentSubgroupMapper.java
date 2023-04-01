package app.converters;

import app.dto.request.StudentSubgroupRequest;
import app.dto.response.StudentSubgroupResponse;
import app.entity.StudentSubgroup;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentSubgroupMapper {

    StudentSubgroupMapper INSTANCE = Mappers.getMapper(StudentSubgroupMapper.class);

    StudentSubgroupResponse entityToResponse(StudentSubgroup studentSubgroup);

    StudentSubgroup requestToEntity(StudentSubgroupRequest studentSubgroupRequest);

}
