package app.converters;

import app.model.mapper.DisciplineMapper;
import app.model.dto.request.DisciplineRequest;
import app.model.dto.response.DisciplineResponse;
import app.model.entity.Discipline;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class DisciplineMapperImpl implements DisciplineMapper {

    @Override
    public Discipline requestToEntity(DisciplineRequest request) {
        if ( request == null ) {
            return null;
        }

        Discipline discipline = new Discipline();

        discipline.setName( request.getName() );

        return discipline;
    }

    @Override
    public DisciplineResponse entityToResponse(Discipline entity) {
        if ( entity == null ) {
            return null;
        }

        DisciplineResponse disciplineResponse = new DisciplineResponse();

        disciplineResponse.setName( entity.getName() );
        disciplineResponse.setPublicId( entity.getPublicId() );

        return disciplineResponse;
    }
}
