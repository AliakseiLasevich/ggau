package app.mappers;

import app.dto.request.CabinetsRequest;
import app.dto.response.CabinetResponse;
import app.entity.Cabinet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CabinetMapper {

    @Mapping(source = "building.publicId", target = "buildingId")
    @Mapping(source = "building.name", target = "buildingName")
    CabinetResponse entityToResponse(Cabinet cabinet);

    Cabinet requestToEntity(CabinetsRequest cabinetsRequest);

    CabinetMapper INSTANCE = Mappers.getMapper(CabinetMapper.class);


}
