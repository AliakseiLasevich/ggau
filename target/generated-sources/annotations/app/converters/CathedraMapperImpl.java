package app.converters;

import app.model.mapper.CathedraMapper;
import app.model.dto.request.CathedraRequest;
import app.model.dto.response.CathedraResponse;
import app.model.dto.response.FacultyResponse;
import app.model.entity.Cathedra;
import app.model.entity.Faculty;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class CathedraMapperImpl implements CathedraMapper {

    @Override
    public Cathedra requestToEntity(CathedraRequest request) {
        if ( request == null ) {
            return null;
        }

        Cathedra cathedra = new Cathedra();

        cathedra.setName( request.getName() );

        return cathedra;
    }

    @Override
    public CathedraResponse entityToResponse(Cathedra entity) {
        if ( entity == null ) {
            return null;
        }

        CathedraResponse cathedraResponse = new CathedraResponse();

        cathedraResponse.setPublicId( entity.getPublicId() );
        cathedraResponse.setName( entity.getName() );
        cathedraResponse.setFaculty( facultyToFacultyResponse( entity.getFaculty() ) );

        return cathedraResponse;
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
