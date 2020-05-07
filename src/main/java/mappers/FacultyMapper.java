package mappers;

import dto.FacultyDto;
import entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import request.FacultyRequestModel;
import response.FacultyRest;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

//    @Mapping(target = "active", source = "facultyDto.active")
    FacultyRest dtoToRest(FacultyDto facultyDto);

    Faculty dtoToEntity(FacultyDto facultyDto);

//    @Mapping(target = "active", source = "faculty.active")
    FacultyDto entityToDto(Faculty faculty);

    FacultyDto requestModelToDto(FacultyRequestModel requestModel);

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

}
