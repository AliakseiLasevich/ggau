package app.model.mapper;

import app.model.dto.request.BuildingRequest;
import app.model.dto.response.BuildingResponse;
import app.model.entity.Building;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BuildingMapper.class, componentModel = "spring")
public interface BuildingMapper extends AbstractMapper<Building, BuildingRequest, BuildingResponse> {
}
