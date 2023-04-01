package app.converters;

import app.model.mapper.BuildingMapper;
import app.model.dto.request.BuildingRequest;
import app.model.dto.response.BuildingResponse;
import app.model.entity.Building;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T14:39:49+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class BuildingMapperImpl implements BuildingMapper {

    @Override
    public Building requestToEntity(BuildingRequest request) {
        if ( request == null ) {
            return null;
        }

        Building building = new Building();

        building.setName( request.getName() );

        return building;
    }

    @Override
    public BuildingResponse entityToResponse(Building entity) {
        if ( entity == null ) {
            return null;
        }

        BuildingResponse buildingResponse = new BuildingResponse();

        buildingResponse.setPublicId( entity.getPublicId() );
        buildingResponse.setName( entity.getName() );

        return buildingResponse;
    }
}
