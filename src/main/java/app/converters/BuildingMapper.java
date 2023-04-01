package app.converters;

import app.dto.request.BuildingRequest;
import app.dto.response.BuildingResponse;
import app.entity.Building;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BuildingMapper.class, componentModel = "spring")
public interface BuildingMapper extends AbstractMapper<Building, BuildingRequest, BuildingResponse> {

    BuildingMapper INSTANCE = Mappers.getMapper(BuildingMapper.class);
}
