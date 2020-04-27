package mappers;

import dto.FacultyDto;
import entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import request.FacultyRequestModel;
import response.FacultyRest;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    Faculty dtoToEntity(FacultyDto facultyDto);

    FacultyDto entityToDto(Faculty faculty);

    FacultyDto requestModelToDto(FacultyRequestModel requestModel);

    FacultyRest toRest(FacultyDto facultyDto);

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

}
