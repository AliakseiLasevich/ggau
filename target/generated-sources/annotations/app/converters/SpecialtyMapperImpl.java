package app.converters;

import app.model.mapper.SpecialtyMapper;
import app.model.dto.request.SpecialtyRequest;
import app.model.dto.response.FacultyResponse;
import app.model.dto.response.SpecialtyResponse;
import app.model.entity.Faculty;
import app.model.entity.Specialty;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class SpecialtyMapperImpl implements SpecialtyMapper {

    @Override
    public SpecialtyResponse entityToResponse(Specialty specialty) {
        if ( specialty == null ) {
            return null;
        }

        SpecialtyResponse specialtyResponse = new SpecialtyResponse();

        specialtyResponse.setPublicId( specialty.getPublicId() );
        specialtyResponse.setName( specialty.getName() );
        specialtyResponse.setCode( specialty.getCode() );
        specialtyResponse.setFaculty( facultyToFacultyResponse( specialty.getFaculty() ) );

        return specialtyResponse;
    }

    @Override
    public Specialty requestToEntity(SpecialtyRequest specialtyRequest) {
        if ( specialtyRequest == null ) {
            return null;
        }

        Specialty specialty = new Specialty();

        specialty.setName( specialtyRequest.getName() );
        specialty.setCode( specialtyRequest.getCode() );

        return specialty;
    }

    protected FacultyResponse facultyToFacultyResponse(Faculty faculty) {
        if ( faculty == null ) {
            return null;
        }

        FacultyResponse facultyResponse = new FacultyResponse();

        facultyResponse.setPublicId( faculty.getPublicId() );
        facultyResponse.setName( faculty.getName() );

        return facultyResponse;
    }
}
