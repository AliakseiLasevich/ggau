package app.converters;

import app.model.mapper.FacultyMapper;
import app.model.dto.request.FacultyRequest;
import app.model.dto.response.FacultyResponse;
import app.model.entity.Faculty;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class FacultyMapperImpl implements FacultyMapper {

    @Override
    public Faculty requestToEntity(FacultyRequest request) {
        if ( request == null ) {
            return null;
        }

        Faculty faculty = new Faculty();

        faculty.setPublicId( request.getPublicId() );
        faculty.setName( request.getName() );

        return faculty;
    }

    @Override
    public FacultyResponse entityToResponse(Faculty entity) {
        if ( entity == null ) {
            return null;
        }

        FacultyResponse facultyResponse = new FacultyResponse();

        facultyResponse.setPublicId( entity.getPublicId() );
        facultyResponse.setName( entity.getName() );

        return facultyResponse;
    }
}
