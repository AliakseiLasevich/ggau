package mappers;

import dto.SpecialtyDto;
import entity.Specialty;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import request.SpecialtyRequestModel;
import response.SpecialtyRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-16T17:54:10+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class SpecialtyMapperImpl implements SpecialtyMapper {

    @Override
    public SpecialtyDto entityToDto(Specialty speciality) {
        if ( speciality == null ) {
            return null;
        }

        SpecialtyDto specialtyDto = new SpecialtyDto();

        specialtyDto.setActive( speciality.isActive() );
        specialtyDto.setId( speciality.getId() );
        specialtyDto.setName( speciality.getName() );
        specialtyDto.setFaculty( speciality.getFaculty() );

        return specialtyDto;
    }

    @Override
    public Specialty dtoToEntity(SpecialtyDto specialtyDto) {
        if ( specialtyDto == null ) {
            return null;
        }

        Specialty specialty = new Specialty();

        if ( specialtyDto.getActive() != null ) {
            specialty.setActive( specialtyDto.getActive() );
        }
        specialty.setId( specialtyDto.getId() );
        specialty.setName( specialtyDto.getName() );
        specialty.setFaculty( specialtyDto.getFaculty() );

        return specialty;
    }

    @Override
    public SpecialtyRest dtoToRest(SpecialtyDto specialtyDto) {
        if ( specialtyDto == null ) {
            return null;
        }

        SpecialtyRest specialtyRest = new SpecialtyRest();

        specialtyRest.setActive( specialtyDto.getActive() );
        specialtyRest.setId( specialtyDto.getId() );
        specialtyRest.setName( specialtyDto.getName() );

        return specialtyRest;
    }

    @Override
    public SpecialtyDto requestToDto(SpecialtyRequestModel requestModel) {
        if ( requestModel == null ) {
            return null;
        }

        SpecialtyDto specialtyDto = new SpecialtyDto();

        specialtyDto.setActive( requestModel.getActive() );
        specialtyDto.setName( requestModel.getName() );
        specialtyDto.setFacultyId( requestModel.getFacultyId() );

        return specialtyDto;
    }
}
