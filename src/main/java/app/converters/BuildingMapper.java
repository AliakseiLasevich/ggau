package app.converters;

import app.dto.request.BuildingRequest;
import app.dto.response.BuildingResponse;
import app.entity.Building;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BuildingMapper {

    Building requestToEntity(BuildingRequest buildingRequest);

    BuildingResponse entityToResponse(Building building);

    BuildingMapper INSTANCE = Mappers.getMapper(BuildingMapper.class);

}
