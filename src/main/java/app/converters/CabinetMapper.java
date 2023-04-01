package app.converters;

import app.dto.request.CabinetsRequest;
import app.dto.response.CabinetResponse;
import app.entity.Cabinet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CabinetMapper.class, componentModel = "spring")
public interface CabinetMapper extends AbstractMapper<Cabinet, CabinetsRequest, CabinetResponse> {

    @Override
    @Mapping(source = "building.publicId", target = "buildingId")
    @Mapping(source = "building.name", target = "buildingName")
    CabinetResponse entityToResponse(Cabinet cabinet);

}
