package app.mappers;

import app.dto.BuildingDto;
import app.entity.Building;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import app.dto.request.BuildingRequest;
import app.dto.response.BuildingResponse;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    @Mapping(source = "active", target = "active")
    BuildingDto entityToDto(Building building);

    @Mapping(source = "active", target = "active")
    Building dtoToEntity(BuildingDto BuildingDto);

    @Mapping(source = "active", target = "active")
    BuildingResponse dtoToRest(BuildingDto BuildingDto);

    @Mapping(source = "active", target = "active")
    BuildingDto requestToDto(BuildingRequest requestModel);

    BuildingMapper INSTANCE = Mappers.getMapper(BuildingMapper.class);

}
