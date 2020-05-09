package mappers;

import dto.FacultyDto;
import entity.Faculty;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import request.FacultyRequestModel;
import response.FacultyRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-09T16:12:02+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class FacultyMapperImpl implements FacultyMapper {

    @Override
    public FacultyRest dtoToRest(FacultyDto facultyDto) {
        if ( facultyDto == null ) {
            return null;
        }

        FacultyRest facultyRest = new FacultyRest();

        facultyRest.setId( facultyDto.getId() );
        facultyRest.setName( facultyDto.getName() );
        facultyRest.setActive( facultyDto.getActive() );

        return facultyRest;
    }

    @Override
    public Faculty dtoToEntity(FacultyDto facultyDto) {
        if ( facultyDto == null ) {
            return null;
        }

        Faculty faculty = new Faculty();

        faculty.setId( facultyDto.getId() );
        faculty.setName( facultyDto.getName() );
        faculty.setActive( facultyDto.getActive() );

        return faculty;
    }

    @Override
    public FacultyDto entityToDto(Faculty faculty) {
        if ( faculty == null ) {
            return null;
        }

        FacultyDto facultyDto = new FacultyDto();

        facultyDto.setId( faculty.getId() );
        facultyDto.setName( faculty.getName() );
        facultyDto.setActive( faculty.getActive() );

        return facultyDto;
    }

    @Override
    public FacultyDto requestModelToDto(FacultyRequestModel requestModel) {
        if ( requestModel == null ) {
            return null;
        }

        FacultyDto facultyDto = new FacultyDto();

        facultyDto.setName( requestModel.getName() );
        facultyDto.setActive( requestModel.getActive() );

        return facultyDto;
    }
}
