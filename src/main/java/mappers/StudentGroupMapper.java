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

    @Mapping(source = "specialtyId", target = "specialty.id")
    StudentGroup dtoToEntity(StudentGroupDto studentGroupDto);


    @Mapping(source = "specialty.id", target = "specialtyId")
    @Mapping(source = "specialty.name", target = "specialtyName")
    StudentGroupDto entityToDto(StudentGroup studentGroup);

    StudentGroupRest dtoToRest(StudentGroupDto studentGroupDto);

    @Mapping(source = "active", target = "active")
    @Mapping(source = "specialtyId", target = "specialty.id")
    StudentGroupDto requestToDto (StudentGroupRequestModel studentGroupRequestModel);

    StudentGroupMapper INSTANCE = Mappers.getMapper(StudentGroupMapper.class);

}
