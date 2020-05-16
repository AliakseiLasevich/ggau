package mappers;

import dto.StudentGroupDto;
import dto.StudentSubgroupDto;
import entity.StudentSubgroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import request.StudentSubgroupRequestModel;
import response.StudentSubgroupRest;

@Mapper(componentModel = "spring")
public interface StudentSubgroupMapper {

    StudentSubgroupRest dtoToRest(StudentSubgroupDto studentSubgroupDto);

    StudentSubgroupDto entityToDto(StudentSubgroup studentSubgroup);

    StudentSubgroup dtoToEntity(StudentSubgroupDto studentSubgroupDto);

    StudentSubgroupDto requestToDto(StudentSubgroupRequestModel studentSubgroupRequestModel);

    StudentSubgroupMapper INSTANCE = Mappers.getMapper(StudentSubgroupMapper.class);

}
