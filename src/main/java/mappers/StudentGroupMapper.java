package mappers;

import dto.StudentGroupDto;
import entity.StudentGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import request.StudentGroupRequestModel;
import response.StudentGroupRest;

@Mapper(componentModel = "spring")
public interface StudentGroupMapper {

    StudentGroup dtoToEntity(StudentGroupDto studentGroupDto);

    @Mapping(source = "specialty.id", target = "specialtyId")
    StudentGroupDto entityToDto(StudentGroup studentGroup);

    StudentGroupRest dtoToRest(StudentGroupDto studentGroupDto);

    @Mapping(source = "active", target = "active")
    StudentGroupDto requestToDto (StudentGroupRequestModel studentGroupRequestModel);

    StudentGroupMapper INSTANCE = Mappers.getMapper(StudentGroupMapper.class);

}
