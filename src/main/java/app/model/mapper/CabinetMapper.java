package app.model.mapper;

import app.model.dto.request.CabinetsRequest;
import app.model.dto.response.CabinetResponse;
import app.model.entity.Cabinet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CabinetMapper.class, componentModel = "spring")
public interface CabinetMapper extends AbstractMapper<Cabinet, CabinetsRequest, CabinetResponse> {

    @Override
    @Mapping(source = "building.publicId", target = "buildingId")
    @Mapping(source = "building.name", target = "buildingName")
    CabinetResponse entityToResponse(Cabinet cabinet);

}
