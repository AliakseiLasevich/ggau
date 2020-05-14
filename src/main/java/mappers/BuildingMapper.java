package mappers;

import dto.BuildingDto;
import entity.Building;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import request.BuildingRequestModel;
import response.BuildingRest;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    @Mapping(source = "active", target = "active")
    BuildingDto entityToDto(Building building);

    @Mapping(source = "active", target = "active")
    Building dtoToEntity(BuildingDto BuildingDto);

    @Mapping(source = "active", target = "active")
    BuildingRest dtoToRest(BuildingDto BuildingDto);

    @Mapping(source = "active", target = "active")
    BuildingDto requestToDto(BuildingRequestModel requestModel);

    BuildingMapper INSTANCE = Mappers.getMapper(BuildingMapper.class);

}
