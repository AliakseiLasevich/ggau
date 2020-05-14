package mappers;

import dto.BuildingDto;
import entity.Building;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import request.BuildingRequestModel;
import response.BuildingRest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-14T18:24:44+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class BuildingMapperImpl implements BuildingMapper {

    @Override
    public BuildingDto entityToDto(Building building) {
        if ( building == null ) {
            return null;
        }

        BuildingDto buildingDto = new BuildingDto();

        buildingDto.setActive( building.getActive() );
        buildingDto.setId( building.getId() );
        buildingDto.setName( building.getName() );

        return buildingDto;
    }

    @Override
    public Building dtoToEntity(BuildingDto BuildingDto) {
        if ( BuildingDto == null ) {
            return null;
        }

        Building building = new Building();

        building.setActive( BuildingDto.getActive() );
        building.setId( BuildingDto.getId() );
        building.setName( BuildingDto.getName() );

        return building;
    }

    @Override
    public BuildingRest dtoToRest(BuildingDto BuildingDto) {
        if ( BuildingDto == null ) {
            return null;
        }

        BuildingRest buildingRest = new BuildingRest();

        buildingRest.setActive( BuildingDto.getActive() );
        buildingRest.setId( BuildingDto.getId() );
        buildingRest.setName( BuildingDto.getName() );

        return buildingRest;
    }

    @Override
    public BuildingDto requestToDto(BuildingRequestModel requestModel) {
        if ( requestModel == null ) {
            return null;
        }

        BuildingDto buildingDto = new BuildingDto();

        buildingDto.setActive( requestModel.getActive() );
        buildingDto.setName( requestModel.getName() );

        return buildingDto;
    }
}
