package mappers;

import dto.FacultyDto;
import entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import request.FacultyRequestModel;
import response.FacultyRest;

@Mapper(componentModel = "spring")
public interface FacultyMapper {


    FacultyRest dtoToRest(FacultyDto facultyDto);

    Faculty dtoToEntity(FacultyDto facultyDto);

    FacultyDto entityToDto(Faculty faculty);

    FacultyDto requestModelToDto(FacultyRequestModel requestModel);

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

}
