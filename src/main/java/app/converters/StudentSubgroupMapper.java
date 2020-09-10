package app.converters;

import app.dto.request.StudentSubgroupRequest;
import app.dto.response.StudentSubgroupResponse;
import app.entity.StudentSubgroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentSubgroupMapper {

    StudentSubgroupResponse entityToResponse(StudentSubgroup studentSubgroup);

    StudentSubgroup requestToEntity(StudentSubgroupRequest studentSubgroupRequest);

    StudentSubgroupMapper INSTANCE = Mappers.getMapper(StudentSubgroupMapper.class);

}
