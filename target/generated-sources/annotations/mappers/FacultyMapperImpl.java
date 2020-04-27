package mappers;

import dto.FacultyDto;
import entity.Faculty;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import request.FacultyRequestModel;
import response.FacultyRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-27T21:10:09+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class FacultyMapperImpl implements FacultyMapper {

    @Override
    public Faculty dtoToEntity(FacultyDto facultyDto) {
        if ( facultyDto == null ) {
            return null;
        }

        Faculty faculty = new Faculty();

        faculty.setName( facultyDto.getName() );

        return faculty;
    }

    @Override
    public FacultyDto entityToDto(Faculty faculty) {
        if ( faculty == null ) {
            return null;
        }

        FacultyDto facultyDto = new FacultyDto();

        facultyDto.setName( faculty.getName() );

        return facultyDto;
    }

    @Override
    public FacultyDto requestModelToDto(FacultyRequestModel requestModel) {
        if ( requestModel == null ) {
            return null;
        }

        FacultyDto facultyDto = new FacultyDto();

        facultyDto.setName( requestModel.getName() );

        return facultyDto;
    }

    @Override
    public FacultyRest toRest(FacultyDto facultyDto) {
        if ( facultyDto == null ) {
            return null;
        }

        FacultyRest facultyRest = new FacultyRest();

        facultyRest.setName( facultyDto.getName() );

        return facultyRest;
    }
}
