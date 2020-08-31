package app.converters;

import app.dto.StudentSubgroupDto;
import app.entity.StudentSubgroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import app.dto.request.StudentSubgroupRequestModel;
import app.dto.response.StudentSubgroupResponse;

@Mapper(componentModel = "spring")
public interface StudentSubgroupMapper {

    StudentSubgroupResponse dtoToRest(StudentSubgroupDto studentSubgroupDto);

    StudentSubgroupDto entityToDto(StudentSubgroup studentSubgroup);

    StudentSubgroup dtoToEntity(StudentSubgroupDto studentSubgroupDto);

    StudentSubgroupDto requestToDto(StudentSubgroupRequestModel studentSubgroupRequestModel);

    StudentSubgroupMapper INSTANCE = Mappers.getMapper(StudentSubgroupMapper.class);

}
