package app.converters;

import app.model.mapper.CabinetMapper;
import app.model.dto.request.CabinetsRequest;
import app.model.dto.response.CabinetResponse;
import app.model.entity.Building;
import app.model.entity.Cabinet;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class CabinetMapperImpl implements CabinetMapper {

    @Override
    public Cabinet requestToEntity(CabinetsRequest request) {
        if ( request == null ) {
            return null;
        }

        Cabinet cabinet = new Cabinet();

        cabinet.setNumber( request.getNumber() );
        cabinet.setMaxStudents( request.getMaxStudents() );
        cabinet.setType( request.getType() );

        return cabinet;
    }

    @Override
    public CabinetResponse entityToResponse(Cabinet cabinet) {
        if ( cabinet == null ) {
            return null;
        }

        CabinetResponse cabinetResponse = new CabinetResponse();

        cabinetResponse.setBuildingId( cabinetBuildingPublicId( cabinet ) );
        cabinetResponse.setBuildingName( cabinetBuildingName( cabinet ) );
        cabinetResponse.setPublicId( cabinet.getPublicId() );
        cabinetResponse.setNumber( cabinet.getNumber() );
        cabinetResponse.setType( cabinet.getType() );
        cabinetResponse.setMaxStudents( cabinet.getMaxStudents() );

        return cabinetResponse;
    }

    private String cabinetBuildingPublicId(Cabinet cabinet) {
        if ( cabinet == null ) {
            return null;
        }
        Building building = cabinet.getBuilding();
        if ( building == null ) {
            return null;
        }
        String publicId = building.getPublicId();
        if ( publicId == null ) {
            return null;
        }
        return publicId;
    }

    private String cabinetBuildingName(Cabinet cabinet) {
        if ( cabinet == null ) {
            return null;
        }
        Building building = cabinet.getBuilding();
        if ( building == null ) {
            return null;
        }
        String name = building.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
