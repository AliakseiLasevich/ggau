package mappers;

import dto.FacultyDto;
import javax.annotation.Generated;
import request.FacultyRequestModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-27T20:39:58+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
public class FacultyMapperCopyImpl implements FacultyMapperCopy {

    @Override
    public FacultyDto requestModelToDto(FacultyRequestModel requestModel) {
        if ( requestModel == null ) {
            return null;
        }

        FacultyDto facultyDto = new FacultyDto();

        facultyDto.setName( requestModel.getName() );

        return facultyDto;
    }
}
